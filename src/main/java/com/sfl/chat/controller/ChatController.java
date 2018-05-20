package com.sfl.chat.controller;

import com.sfl.chat.service.UserService;
import com.sfl.chat.service.ChatMessageService;
import com.sfl.chat.service.ChatRoomService;
import com.sfl.chat.dto.ChatMessageDto;
import com.sfl.chat.domain.ChatMessage;
import com.sfl.chat.domain.User;
import com.sfl.chat.domain.ChatRoom;

import java.util.List;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * controller for chat room.
 *
 * @author Sevak Gharibian
 */
@Controller
public class ChatController {

    private static final Logger LOG = LoggerFactory.getLogger(ChatController.class);

    private static final long DEFAULT_CHAT_ROOM_ID = 1L;

    private final UserService userService;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatController(UserService userService,
                          ChatMessageService chatMessageService,
                          ChatRoomService chatRoomService) {
        this.userService = userService;
        this.chatMessageService = chatMessageService;
        this.chatRoomService = chatRoomService;
    }

    @MessageMapping("/message")
    @SendTo("/topic/chatroom")
    public ChatMessageDto sendMessage(@Payload ChatMessageDto chatMessageDto) {
        // chat room message handling here

        // save chat message
        User user = userService.findByUsername(chatMessageDto.getSender());
        ChatRoom chatRoom = chatRoomService.findById(DEFAULT_CHAT_ROOM_ID);
        ChatMessage message = new ChatMessage();
        message.setContent(chatMessageDto.getContent());
        message.setChatRoom(chatRoom);
        message.setSender(user);
        message.setCreatedDateTime(LocalDateTime.now());
        chatMessageService.save(message);

        // check message for bad words
        // if message contains badword, do not broadcast
        // set message deleted
        if (chatMessageDto.getContent().contains(chatRoom.getBadWord())) {
            message.setDeleted(true);
            chatMessageService.save(message);
            return null;
        }        

        // broadcast message to chat room users
        return chatMessageDto;
    }
}


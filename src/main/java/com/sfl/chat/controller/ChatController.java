package com.sfl.chat.controller;

import com.sfl.chat.service.UserService;
import com.sfl.chat.dto.ChatMessage;

import java.util.List;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

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

    private final UserService userService;

    @Autowired
    public ChatController(UserService userService) {
        this.userService = userService;
    }

    @MessageMapping("/message")
    @SendTo("/topic/chatroom")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        // chat room message handling here
        return chatMessage;
    }
}


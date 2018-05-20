package com.sfl.chat.controller;

import com.sfl.chat.domain.User;
import com.sfl.chat.service.UserService;
import com.sfl.chat.domain.ChatMessage;
import com.sfl.chat.domain.ChatRoom;
import com.sfl.chat.service.ChatMessageService;
import com.sfl.chat.service.ChatRoomService;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * controller for user profile.
 *
 * @author Sevak Gharibian
 */
@Controller
public class ProfileController {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileController.class);

    private static final long DEFAULT_CHAT_ROOM_ID = 1L;

    private final UserService userService;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

    @Autowired
    public ProfileController(UserService userService,
                             ChatMessageService chatMessageService,
                             ChatRoomService chatRoomService) {
        this.userService = userService;
        this.chatMessageService = chatMessageService;
        this.chatRoomService = chatRoomService;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('profile_view')")
    public String view(Model model, Principal principal, HttpServletRequest request) {        
        User user = userService.findByUsername(principal.getName());
        // get picture url
        String  pictureUrl = ServletUriComponentsBuilder
                         .fromContextPath(request)
                         .path("/pictures/" + user.getPictureFileName())
                         .build()
                         .toUriString();

        List<ChatMessage> messages = chatMessageService.findByChatRoomIdAndDeletedFalse(DEFAULT_CHAT_ROOM_ID);
        ChatRoom chatRoom = chatRoomService.findByIdWithUsers(DEFAULT_CHAT_ROOM_ID);
        boolean userInChatRoom = true;
        if (!chatRoom.getUsers().contains(user)) {
            userInChatRoom = false;
        }
        model.addAttribute("user", user);
        model.addAttribute("picurl", pictureUrl);
        model.addAttribute("messages", messages);
        model.addAttribute("userInChatRoom", userInChatRoom);
        return "profile";
    }
}

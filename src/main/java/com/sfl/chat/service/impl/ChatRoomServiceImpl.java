package com.sfl.chat.service.impl;

import com.sfl.chat.domain.ChatRoom;
import com.sfl.chat.repository.ChatRoomRepository;
import com.sfl.chat.service.ChatRoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * service implementation for ChatRoom.
 *
 * @author Sevak Gharibian
 */
@Service
public class ChatRoomServiceImpl extends BaseServiceImpl<ChatRoom> implements ChatRoomService {

  private final ChatRoomRepository chatRoomRepository;

  @Autowired
  public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository) {
    super(chatRoomRepository);
    this.chatRoomRepository = chatRoomRepository;
  }
}

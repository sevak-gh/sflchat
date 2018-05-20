package com.sfl.chat.service.impl;

import com.sfl.chat.domain.ChatMessage;
import com.sfl.chat.repository.ChatMessageRepository;
import com.sfl.chat.service.ChatMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * service implementation for ChatMessage.
 *
 * @author Sevak Gharibian
 */
@Service
public class ChatMessageServiceImpl extends BaseServiceImpl<ChatMessage> implements ChatMessageService {

  private final ChatMessageRepository chatMessageRepository;

  @Autowired
  public ChatMessageServiceImpl(ChatMessageRepository chatMessageRepository) {
    super(chatMessageRepository);
    this.chatMessageRepository = chatMessageRepository;
  }
}

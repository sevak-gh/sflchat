package com.sfl.chat.repository;

import com.sfl.chat.domain.ChatMessage;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.Repository;

/**
 * repository for ChatMessage.
 *
 * @author Sevak Gharibian
 */
public interface ChatMessageRepository extends BaseRepository<ChatMessage>, Repository<ChatMessage, Long> {
}

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
    @Query("SELECT chatMessage FROM ChatMessage chatMessage LEFT JOIN FETCH chatMessage.sender "
           + "LEFT JOIN FETCH chatMessage.chatRoom chatRoom " 
           + "WHERE chatMessage.deleted = false AND chatRoom.id = :chatRoomId")
    List<ChatMessage> findByChatRoomIdAndDeletedFalse(@Param("chatRoomId") Long chatRoomId);
}

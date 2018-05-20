package com.sfl.chat.repository;

import com.sfl.chat.domain.ChatRoom;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.Repository;

/**
 * repository for ChatRoom.
 *
 * @author Sevak Gharibian
 */
public interface ChatRoomRepository extends BaseRepository<ChatRoom>, Repository<ChatRoom, Long> {
}

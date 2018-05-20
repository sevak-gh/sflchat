package com.sfl.chat.service;

import com.sfl.chat.domain.ChatRoom;

/**
 * service interface for ChatRoom.
 *
 * @author Sevak Gharibian
 */
public interface ChatRoomService extends BaseService<ChatRoom> {
    ChatRoom findByIdWithUsers(Long id);
}

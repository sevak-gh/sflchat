package com.sfl.chat.dto;

/**
 * chat message dto.
 *
 * @author Sevak Gharibian
 */
public class ChatMessage {

    private String content;
    private String sender;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;   
    }
}

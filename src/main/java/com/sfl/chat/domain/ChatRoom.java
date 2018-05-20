package com.sfl.chat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.HashSet;

/**
 * simple business object representing a chat room.
 *
 * @author Sevak Gharibian
 */
@Entity
@Table(name = "chat_room")
public class ChatRoom extends BaseEntity {

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "chat_room_user",
            joinColumns = {@JoinColumn(name = "fk_chat_room", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "fk_user", nullable = false)})
    private Set<User> users = new HashSet<User>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

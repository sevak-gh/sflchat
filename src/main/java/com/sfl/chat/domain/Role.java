package com.sfl.chat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

/**
 * simple business object representing a role.
 *
 * @author Sevak Gharibian
 */
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "fk_role", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "fk_permission", nullable = false)})
    private Set<Permission> permissions = new HashSet<Permission>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return String.format("Role[id:%d, name:%s]", id, name);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Role)) {
            return false;
        }
        Role role = (Role) other;
        if (this.id == role.id) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (id != null) {
            result = (31 * result) + id.hashCode();
        }
        return result;
    }
}

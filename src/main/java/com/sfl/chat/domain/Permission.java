package com.sfl.chat.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

/**
 * simple business object representing a permission.
 *
 * @author Sevak Gharibian
 *
 */
@Entity
@Table(name="permission")
public class Permission extends BaseEntity {

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Permission[id:%d, name:%s]", id, name);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Permission)) {
            return false;
        }        
        Permission permission = (Permission)other; 
        if (this.id == permission.id) {
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

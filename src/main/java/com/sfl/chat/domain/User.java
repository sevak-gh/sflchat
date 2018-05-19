package com.sfl.chat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

/**
 * simple business object representing a user.
 * base class for any kind of system user.
 *
 * @author Sevak Gharibian
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity implements UserDetails  {

    @NotNull
    @Size(max = 50)
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @NotNull
    @Size(max = 100)
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "expiration_date", nullable = true)
    private LocalDateTime expirationDate;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Size(max = 15)
    @Column(name = "remote_ip", nullable = true, length = 15)
    private String remoteIp;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "fk_user", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "fk_role", nullable = false)})
    private Set<Role> roles = new HashSet<Role>();

    @Column(name = "locked", nullable = false)
    private boolean locked = false;

    @NotNull
    @Size(max = 50)
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotNull
    @Size(max = 50)
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Size(max = 100)
    @Column(name = "picture_file_name", nullable = true, length = 100)
    private String pictureFileName;

    @Transient
    private boolean accountNonExpired = true;

    @Transient
    private boolean credentialsNonExpired = true;

    @Transient
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.locked = !accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getFirstName() {  
        return firstName;    
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {  
        return lastName;    
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPictureFileName() {  
        return pictureFileName;    
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }
  
    @Override
    public String toString() {
        return String.format("User[id:%d, username:%s, expireationDate:%s, remoteIp: %s, enabled: %s]",
                id, username, expirationDate, remoteIp, String.valueOf(enabled));
    }
}

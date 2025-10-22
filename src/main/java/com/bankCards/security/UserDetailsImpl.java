package com.bankCards.security;

import com.bankCards.entity.Enums.Role;
import com.bankCards.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@EqualsAndHashCode(of = "username")
@ToString(exclude = "password")
@Builder
public class UserDetailsImpl implements UserDetails {
    private final Long id;
    private final String username;

    @JsonIgnore
    private final String password;

    private final Role role;

    private final boolean accountNonLocked;
    private final boolean enabled;

    private final Collection<? extends GrantedAuthority> authorities;

    // Конструктор из сущности User
    public static UserDetailsImpl fromUser(User user) {
        return UserDetailsImpl.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .accountNonLocked(true) // можно заменить на user.isAccountNonLocked()
                .enabled(true)           // можно заменить на user.isEnabled()
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())))
                .build();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

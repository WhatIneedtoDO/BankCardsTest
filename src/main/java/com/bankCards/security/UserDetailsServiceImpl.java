package com.bankCards.security;

import com.bankCards.exception.UserNotFoundException;
import com.bankCards.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Попытка загрузить пользователя: {}", username);

        User user = (User) userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.warn("Пользователь не найден: {}", username);
                    return new UsernameNotFoundException("Пользователь не найден: " + username);
                });

        log.info("Пользователь успешно найден: {}", username);
        return UserDetailsImpl.fromUser(user);
    }
}

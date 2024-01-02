package com.afirma.test.bookings.service;

import com.afirma.test.bookings.common.dto.UserDTO;
import com.afirma.test.bookings.mapper.UserMapper;
import com.afirma.test.bookings.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.afirma.test.bookings.repository.entity.User user = userRepository.findByName(username)
                .orElse(new com.afirma.test.bookings.repository.entity.User());
        return User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    public void save(UserDTO userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        com.afirma.test.bookings.repository.entity.User user = UserMapper.INSTANCE.dtoToEntity(userDto);
        userRepository.save(user);
    }
}

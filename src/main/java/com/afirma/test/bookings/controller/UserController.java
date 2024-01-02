package com.afirma.test.bookings.controller;

import com.afirma.test.bookings.common.dto.RoomDTO;
import com.afirma.test.bookings.common.dto.UserDTO;
import com.afirma.test.bookings.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
@Log4j2
@RequiredArgsConstructor
public class UserController {

    private final CustomUserDetailService userDetailsService;
    @PostMapping
    public void save( @RequestBody UserDTO user){
        userDetailsService.save(user);
    }
}

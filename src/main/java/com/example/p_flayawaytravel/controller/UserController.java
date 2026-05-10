package com.example.p_flayawaytravel.controller;

import com.example.p_flayawaytravel.dto.RequestUserDTO;
import com.example.p_flayawaytravel.dto.ResponseUserDTO;
import com.example.p_flayawaytravel.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseUserDTO> register(@Valid @RequestBody RequestUserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(dto));
    }
}
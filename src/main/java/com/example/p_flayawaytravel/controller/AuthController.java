package com.example.p_flayawaytravel.controller;

import com.example.p_flayawaytravel.dto.RequestLoginDTO;
import com.example.p_flayawaytravel.dto.ResponseLoginDTO;
import com.example.p_flayawaytravel.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDTO> login(@Valid @RequestBody RequestLoginDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}
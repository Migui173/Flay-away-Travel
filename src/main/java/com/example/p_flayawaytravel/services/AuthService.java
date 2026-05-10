package com.example.p_flayawaytravel.services;

import com.example.p_flayawaytravel.dto.RequestLoginDTO;
import com.example.p_flayawaytravel.dto.ResponseLoginDTO;
import com.example.p_flayawaytravel.repository.UserRepository;
import com.example.p_flayawaytravel.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public ResponseLoginDTO login(RequestLoginDTO dto) {
        // Valida email y contraseña — lanza excepción si falla
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        UserDetails user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);
        return new ResponseLoginDTO(token);
    }
}

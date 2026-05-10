package com.example.p_flayawaytravel.services;

import com.example.p_flayawaytravel.domain.User;
import com.example.p_flayawaytravel.dto.RequestUserDTO;
import com.example.p_flayawaytravel.dto.ResponseUserDTO;
import com.example.p_flayawaytravel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public ResponseUserDTO register(RequestUserDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User saved = userRepository.save(user);

        ResponseUserDTO response = new ResponseUserDTO();
        response.setId(saved.getId());
        return response;
    }
}
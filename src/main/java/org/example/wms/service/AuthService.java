package org.example.wms.service;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.AuthRequestDTO;
import org.example.wms.dto.AuthResponseDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.repository.UserRepository;
import org.example.wms.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public ApiResponse<AuthResponseDTO> login(AuthRequestDTO request) {
        if (!validateUser(request.getUsername(), request.getPassword())) {
            return new ApiResponse<>(401, "Invalid username or password");
        }

        String token = jwtUtil.generateToken(request.getUsername());
        return new ApiResponse<>(200, "SUCCESS", new AuthResponseDTO(token), 0);
    }

    private boolean validateUser(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(rawPassword, user.getPasswordHash()))
                .orElse(false);
    }
}

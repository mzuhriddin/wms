package org.example.wms.service;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.AuthRequestDTO;
import org.example.wms.dto.AuthResponseDTO;
import org.example.wms.dto.UserDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.entity.UserEntity;
import org.example.wms.mapper.UserMapper;
import org.example.wms.repository.UserRepository;
import org.example.wms.utils.JwtUtil;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public ApiResponse<AuthResponseDTO> login(AuthRequestDTO request) {
        if (!validateUser(request.getUsername(), request.getPassword())) {
            return new ApiResponse<>(401, "Invalid username or password");
        }

        String token = jwtUtil.generateToken(request.getUsername());
        return new ApiResponse<>(200, "SUCCESS", new AuthResponseDTO(token), 0);
    }

    public ApiResponse<UserDTO> getMe(User user) {
        Optional<UserEntity> byUsername = userRepository.findByUsername(user.getUsername());
        if (byUsername.isPresent()) {
            UserEntity userEntity = byUsername.get();
            UserDTO dto = userMapper.toDTO(userEntity);
            return new ApiResponse<>(200, "SUCCESS", dto, 0);
        }
        return new ApiResponse<>(401, "Bad credentials");
    }

    private boolean validateUser(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(rawPassword, user.getPasswordHash()))
                .orElse(false);
    }
}

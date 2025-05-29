package org.example.wms.service;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.UserDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.entity.User;
import org.example.wms.mapper.UserMapper;
import org.example.wms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public ApiResponse<List<UserDTO>> getAllUsers() {
        return new ApiResponse<>(200, "SUCCESS", repository.findAll().stream()
                .map(mapper::toDTO)
                .toList());
    }

    public ApiResponse<UserDTO> createUser(UserDTO dto) {
        return new ApiResponse<>(200, "SUCCESS", mapper.toDTO(repository.save(mapper.toEntity(dto))));
    }

    public ApiResponse<UserDTO> getUserById(Long id) {
        return repository.findById(id).map(mapper::toDTO)
                .map(dto -> new ApiResponse<>(200, "SUCCESS", dto))
                .orElseGet(() -> new ApiResponse<>(404, "NOT FOUND"));
    }

    public ApiResponse<UserDTO> updateUser(Long id, UserDTO dto) {
        Optional<UserDTO> optional = repository.findById(id).map(existingUser -> {
            existingUser.setUsername(dto.getUsername());
            existingUser.setRole(dto.getRole());
            User updatedUser = repository.save(existingUser);
            return mapper.toDTO(updatedUser);
        });
        return optional.map(user -> new ApiResponse<>(200, "SUCCESS", user))
                .orElseGet(() -> new ApiResponse<>(404, "NOT FOUND"));
    }

    public ApiResponse<Void> deleteUser(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return new ApiResponse<>(200, "SUCCESS");
        }
        return new ApiResponse<>(404, "NOT FOUND");
    }
}
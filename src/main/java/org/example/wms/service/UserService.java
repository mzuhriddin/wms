package org.example.wms.service;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.UserDTO;
import org.example.wms.entity.User;
import org.example.wms.mapper.UserMapper;
import org.example.wms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .toList();
    }

    public UserDTO createUser(UserDTO dto) {
        User user = userMapper.toEntity(dto);
        return userMapper.toDTO(userRepository.save(user));
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toDTO);
    }

    public Optional<UserDTO> updateUser(Long id, UserDTO dto) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(dto.getUsername());
            existingUser.setRole(dto.getRole());
            User updatedUser = userRepository.save(existingUser);
            return userMapper.toDTO(updatedUser);
        });
    }

    public boolean deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
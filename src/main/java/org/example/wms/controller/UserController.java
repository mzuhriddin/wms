package org.example.wms.controller;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.UserDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.dto.general.PaginationDTO;
import org.example.wms.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers(PaginationDTO pagination) {
        return ResponseEntity.ok(userService.getAll(pagination));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable Long id) {
        ApiResponse<UserDTO> response = userService.getById(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        ApiResponse<UserDTO> response = userService.update(id, dto);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        ApiResponse<Void> response = userService.delete(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}


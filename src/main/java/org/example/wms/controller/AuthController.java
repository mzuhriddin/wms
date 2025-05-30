package org.example.wms.controller;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.AuthRequestDTO;
import org.example.wms.dto.AuthResponseDTO;
import org.example.wms.dto.UserDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> login(@RequestBody AuthRequestDTO authRequestDTO) {
        ApiResponse<AuthResponseDTO> resp = authService.login(authRequestDTO);
        return ResponseEntity.status(resp.getCode()).body(resp);
    }

    @GetMapping("me")
    public ResponseEntity<ApiResponse<UserDTO>> me(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(authService.getMe(user));
    }
}

package org.example.wms.controller;

import lombok.RequiredArgsConstructor;
import org.example.wms.dto.AuthRequestDTO;
import org.example.wms.dto.AuthResponseDTO;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

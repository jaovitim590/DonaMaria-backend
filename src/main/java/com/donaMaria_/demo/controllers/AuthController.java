package com.donaMaria_.demo.controllers;

import com.donaMaria_.demo.Dtos.LoginDto;
import com.donaMaria_.demo.Dtos.MeDto;
import com.donaMaria_.demo.Dtos.ReqUserDto;
import com.donaMaria_.demo.exceptions.EmailJaCadastradoException;
import com.donaMaria_.demo.models.User;
import com.donaMaria_.demo.services.JwtService;
import com.donaMaria_.demo.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder;

    public AuthController(UserService userService,JwtService jwtService,BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid ReqUserDto dto){
        if (userService.existByEmail(dto.email())){
            throw new EmailJaCadastradoException();
        }
        User user = userService.createUser(dto);
        String token = jwtService.generateToken(user);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto dto){
        try {
            User user = userService.findByEmail(dto.email());

            if (user == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(createErrorResponse("credenciais invalidas!"));
            }

            if (!encoder.matches(dto.password(), user.getPassword())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(createErrorResponse("credenciais invalidas!"));
            }

            String token = jwtService.generateToken(user);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("type", "bearer");

            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Erro ao processar login"));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpServletRequest request) {
        Optional<String> emailOpt = extractEmail(request);

        if (emailOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(createErrorResponse("Token inválido ou não fornecido"));
        }

        try {
            User user = userService.findByEmail(emailOpt.get());

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(createErrorResponse("Usuário não encontrado"));
            }

            MeDto userInfo = new MeDto(
                    user.getEmail(),
                    user.getName(),
                    user.getRole().name()
            );

            return ResponseEntity.ok(userInfo);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Erro ao buscar informações do usuário"));
        }
    }

    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        return error;
    }
    private Optional<String> extractEmail(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Optional.empty();
        }

        try {
            String token = authHeader.substring(7);
            String email = jwtService.extractEmail(token);
            return Optional.ofNullable(email);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

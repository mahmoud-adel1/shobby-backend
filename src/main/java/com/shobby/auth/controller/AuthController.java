package com.shobby.auth.controller;

import com.shobby.auth.dto.internal.LoginResult;
import com.shobby.auth.dto.internal.RefreshResult;
import com.shobby.auth.dto.internal.RegisterResult;
import com.shobby.auth.dto.request.LoginRequestDto;
import com.shobby.auth.dto.request.RegisterRequestDto;
import com.shobby.auth.dto.response.LoginResponseDto;
import com.shobby.auth.dto.response.RefreshResponseDto;
import com.shobby.auth.dto.response.RegisterResponseDto;
import com.shobby.auth.service.AuthService;
import com.shobby.security.jwt.InvalidRefreshTokenException;
import com.shobby.security.jwt.RefreshTokenService;
import com.shobby.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest, HttpServletResponse httpServletResponse) {
        LoginResult loginResult = authService.login(loginRequest);

        ResponseCookie refreshToken = ResponseCookie.from("refreshToken", loginResult.getRefreshToken())
                .httpOnly(true)
                .secure(false)
                .path("/auth")
                .maxAge(Duration.ofDays(7))
                .sameSite("Strict")
                .build();
        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, refreshToken.toString());

        LoginResponseDto response = LoginResponseDto
                .builder()
                .username(loginResult.getUsername())
                .accessToken(loginResult.getAccessToken())
                .message("User logged in successfully.")
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto registerRequest, HttpServletResponse httpServletResponse) {
        RegisterResult result = authService.register(registerRequest);

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", result.getRefreshToken())
                .httpOnly(true)
                .secure(false)
                .path("/auth")
                .maxAge(Duration.ofDays(7))
                .sameSite("Strict")
                .build();

        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
        RegisterResponseDto response = RegisterResponseDto.builder()
                .username(result.getUsername())
                .accessToken(result.getAccessToken())
                .message("User registered successfully.")
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponseDto> refreshToken(HttpServletRequest request, HttpServletResponse httpServletResponse) {

        String oldRefreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()->new InvalidRefreshTokenException("RefreshToken is invalid."));

        RefreshResult refreshResult = authService.refresh(oldRefreshToken);


        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshResult.getRefreshToken())
                .httpOnly(true)
                .secure(false)
                .path("/auth")
                .maxAge(Duration.ofDays(7))
                .sameSite("Strict")
                .build();

        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
        RefreshResponseDto response = new RefreshResponseDto(refreshResult.getAccessToken());

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()->new InvalidRefreshTokenException("RefreshToken is invalid."));
        refreshTokenService.revoke(refreshToken);

        ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/auth")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, deleteCookie.toString());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Logged out successfully.");
    }

}

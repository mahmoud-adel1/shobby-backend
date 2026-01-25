package com.shobby.auth.service;

import com.shobby.auth.dto.internal.LoginResult;
import com.shobby.auth.dto.internal.RefreshResult;
import com.shobby.auth.dto.internal.RegisterResult;
import com.shobby.auth.dto.request.LoginRequestDto;
import com.shobby.auth.dto.request.RegisterRequestDto;
import com.shobby.auth.exception.AuthUserAlreadyExistsException;
import com.shobby.auth.mapper.UserRegisterMapper;
import com.shobby.role.enums.RoleType;
import com.shobby.role.service.RoleService;
import com.shobby.security.config.CustomUserDetails;
import com.shobby.security.jwt.RefreshTokenInvalidException;
import com.shobby.security.jwt.JwtService;
import com.shobby.security.jwt.RefreshTokenService;
import com.shobby.user.entity.User;
import com.shobby.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final RoleService roleService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public RegisterResult register(RegisterRequestDto registerRequest) {

        if (userService.isUserExistByUsername(registerRequest.getUsername())) {
            throw new AuthUserAlreadyExistsException();
        }

        if (userService.isUserExistByEmail(registerRequest.getEmail())) {
            throw new AuthUserAlreadyExistsException();
        }

        if (userService.isUserExistByMobileNumber(registerRequest.getMobileNumber())) {
            throw new AuthUserAlreadyExistsException();
        }

        User user = UserRegisterMapper.mapToUser(registerRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleService.findByRoleType(RoleType.CUSTOMER));
        user.setEnabled(true);
        user = userService.save(user);

        UserDetails userDetails = new CustomUserDetails(user);
        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = refreshTokenService.create(user.getUsername());
        return RegisterResult.builder()
                .username(user.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public RefreshResult refresh(String refreshToken) {

        String username = refreshTokenService.getUsernameByRefreshToken(refreshToken);
        if (username != null) {
            if (refreshTokenService.validate(username, refreshToken)) {
                Optional<User> optionalUser = userService.getUserByUsername(username);
                if (optionalUser.isPresent()) {
                    UserDetails userDetails = new CustomUserDetails(optionalUser.get());
                    refreshTokenService.revoke(username);
                    String accessToken = jwtService.generateAccessToken(userDetails);
                    String newRefreshToken = refreshTokenService.create(userDetails.getUsername());
                    return RefreshResult
                            .builder()
                            .accessToken(accessToken)
                            .refreshToken(newRefreshToken)
                            .build();
                }
            }
        }
        throw new RefreshTokenInvalidException();
    }

    public LoginResult login(LoginRequestDto loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(Objects.requireNonNull(userDetails));
        String refreshToken = refreshTokenService.create(userDetails.getUsername());
        return LoginResult
                .builder()
                .username(userDetails.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}

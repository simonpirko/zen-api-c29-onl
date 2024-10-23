package by.tms.zenapic29onl.controller;

import by.tms.zenapic29onl.dto.RequestLoginDto;
import by.tms.zenapic29onl.security.JwtUtil;
import by.tms.zenapic29onl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RequestLoginDto requestLoginDto) {
        UserDetails userDetails = userService.loadUserByUsername(requestLoginDto.username());

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), requestLoginDto.password(), userDetails.getAuthorities())
        );

        String s = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(s);
    }
}

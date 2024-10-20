package by.tms.zenapic29onl.controller;

import by.tms.zenapic29onl.dto.AuthenticationRequestDto;
import by.tms.zenapic29onl.entity.User;
import by.tms.zenapic29onl.security.JwtUtil;
import by.tms.zenapic29onl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/reg")
    public ResponseEntity<User> create(@RequestBody User user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());

        User save = userService.save(user);

        return ResponseEntity.ok(save);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AuthenticationRequestDto authenticationRequest) {
        UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.username());
        String jwt = jwtUtil.generateToken(userDetails);
        return jwt;
    }
}

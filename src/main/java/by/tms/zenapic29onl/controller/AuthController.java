package by.tms.zenapic29onl.controller;

import by.tms.zenapic29onl.entity.User;
import by.tms.zenapic29onl.security.JwtComponent;
import by.tms.zenapic29onl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtComponent jwtComponent;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/auth")
    public String authenticate(@RequestBody User user) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new Exception("Invalid Username or Password");
        }
        UserDetails userDetails = userService.loadByUsername(user.getUsername());
        return jwtComponent.generateToken(userDetails);
    }

    @PostMapping("/reg")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "Registered Successfully";
    }
}
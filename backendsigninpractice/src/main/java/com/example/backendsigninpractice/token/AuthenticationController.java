    package com.example.backendsigninpractice.token;



    // import com.example.backendsigninpractice.service.JwtUtil;
    // import com.example.backendsigninpractice.service.CustomUserDetailsService;
    // import com.example.backendsigninpractice.model.AuthenticationRequest;
    // import com.example.backendsigninpractice.model.AuthenticationResponse;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import com.example.backendsigninpractice.model.AuthenticationResponse;
    import com.example.backendsigninpractice.token.service.CustomUserDetailsService;
    import com.example.backendsigninpractice.token.service.JwtUtil;

    @RestController
    @RequestMapping("/authenticate")
    public class AuthenticationController {

        @Autowired
        private JwtUtil jwtUtil;

        @Autowired
        private CustomUserDetailsService customUserDetailsService;

        @PostMapping
        public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
            // Here, you can authenticate against the database (user validation) based on the username and password.
            final String jwt = jwtUtil.generateToken(authenticationRequest.getUsername());
            return new AuthenticationResponse(jwt);
        }
    }

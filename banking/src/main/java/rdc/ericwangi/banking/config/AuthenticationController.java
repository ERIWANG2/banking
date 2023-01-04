package rdc.ericwangi.banking.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import rdc.ericwangi.banking.dto.AuthenticationRequest;
import rdc.ericwangi.banking.dto.AuthenticationResponse;
import rdc.ericwangi.banking.dto.UserDto;
import rdc.ericwangi.banking.repository.UserRepository;
import rdc.ericwangi.banking.services.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>register(
            @RequestBody UserDto userDto
    ){
        return ResponseEntity.ok(userService.register(userDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>autheticate(
            @RequestBody AuthenticationRequest request
    ){
        //essayons d'authentifier l'utilisateur
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
       );
       //Une fois authentifié
        final UserDetails user = userRepository.findByEmail(request.getEmail()).get();
        final String token = jwtUtils.generateToken(user);

        return ResponseEntity.ok(
                AuthenticationResponse.builder()
                        .token(token)
                        .build()
        );
    }


}

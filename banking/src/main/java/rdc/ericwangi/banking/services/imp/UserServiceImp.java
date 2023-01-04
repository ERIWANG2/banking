package rdc.ericwangi.banking.services.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rdc.ericwangi.banking.config.JwtUtils;
import rdc.ericwangi.banking.dto.AccountDto;
import rdc.ericwangi.banking.dto.AuthenticationRequest;
import rdc.ericwangi.banking.dto.AuthenticationResponse;
import rdc.ericwangi.banking.dto.UserDto;
import rdc.ericwangi.banking.models.Role;
import rdc.ericwangi.banking.models.User;
import rdc.ericwangi.banking.repository.RoleRepository;
import rdc.ericwangi.banking.repository.UserRepository;
import rdc.ericwangi.banking.services.AccountService;
import rdc.ericwangi.banking.services.UserService;
import rdc.ericwangi.banking.validators.ObjectsValidator;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final ObjectsValidator<UserDto> validator;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;
    private static final String ROLE_USER = "ROLE_USER";
    private final RoleRepository roleRepository;
    private final AuthenticationManager authManager;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto ::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(UserDto ::fromEntity)
                .orElseThrow(() ->
                        new EntityNotFoundException("Utilisateur n'existe pas avec c'est ID:"+id));
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Integer validateAccount(Integer id) {
        //on doit verifier si l'utilisateur existe. Si existe on active
        User user = userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(
                        "Utilisateur n'existe pas pour la validation avec le ID:"+id));
        user.setActive(true);
        //créer un compte bancaire pour cet utilisateur
        AccountDto account = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountService.save(account);
        //enregistrer l'utilisateur
        userRepository.save(user);
        //retourner id de l'utilisateur
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        //on doit verifier si l'utilisateur existe. Si existe on active
        User user = userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(
                        "Utilisateur n'existe pas pour la validation avec le ID:"+id));
        user.setActive(false);
        //enregistrer l'utilisateur
        userRepository.save(user);
        //retourner id de l'utilisateur
        return user.getId();
    }

    @Override
    @Transactional
    public AuthenticationResponse register(UserDto userDto) {
        validator.validate(userDto);
        User user = UserDto.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(
                findOrCreateRole(ROLE_USER)
        );

        var saveUser =userRepository.save(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId",saveUser.getId());
        claims.put("fullname",saveUser.getPrenom()+ " "+saveUser.getNom());

        String token = jwtUtils.generateToken(saveUser,claims);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

       final User user = userRepository.findByEmail(request.getEmail()).get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId",user.getId());
        claims.put("fullname",user.getPrenom()+ " "+user.getNom());
        String token = jwtUtils.generateToken(user,claims);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private Role findOrCreateRole(String nomRole) {
        Role role = roleRepository.findByNomRole(nomRole)
                .orElse(null);
        if (role == null) {
            return roleRepository.save(
                    Role.builder()
                            .nomRole(nomRole)
                            .build()
            );
        }
        return role;
    }
}

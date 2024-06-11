package com.example.accapplication.service;

import com.example.accapplication.Repository.RolesRepository;
import com.example.accapplication.Repository.UserRepository;
import com.example.accapplication.dto.LoginResponseDto;
import com.example.accapplication.model.Roles;
import com.example.accapplication.model.Users;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    private final ModelMapper modelMapper;

    public String registerUser(String username, String password){
        String encodedPassword = passwordEncoder.encode(password);
        Roles userRole = rolesRepository.findByAuthority("ADMIN").get();
        Set<Roles> authorities = new HashSet<>();
        authorities.add(userRole);
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(encodedPassword);
        users.setAuthorities(authorities);
        userRepository.save(users);

        return "Your username is ".concat(users.getUsername());

    }


    public String updateRoles(Integer roleId, String authority){
        Optional<Roles> rolesOptional = rolesRepository.findRolesByRoleId(roleId);
        if(rolesOptional.isEmpty()){
            throw new RuntimeException("Roles Not Found");
        }else {
            Roles  roles = rolesOptional.get();
            roles.setAuthority(authority.toUpperCase());
            rolesRepository.save(roles);
            return "Your roles is: ".concat(authority);
        }
    }

    public LoginResponseDto loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDto(userRepository.findByUsername(username).get(), token);

        } catch (BadCredentialsException e) {
            return new LoginResponseDto(null, "Incorrect email or password");
        } catch (AuthenticationException e) {
            return new LoginResponseDto(null, "Authentication failed: " + e.getMessage());
        }
    }

}

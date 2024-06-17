package com.softand.demo.service;

import com.softand.demo.configuration.security.JwtUtils;
import com.softand.demo.models.Role;
import com.softand.demo.models.RoleEnum;
import com.softand.demo.models.Usuario;
import com.softand.demo.models.dto.RegisterInput;
import com.softand.demo.models.dto.LoginInput;
import com.softand.demo.models.dto.AuthResponse;
import com.softand.demo.repositories.RoleRepository;
import com.softand.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Usuario userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(userEntity.getRole().getRoleName().toString())));

        userEntity.getRole().getPermissions()
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.isEnabled(),
                userEntity.isAccountNoExpired(), userEntity.isCredentialNoExpired(), userEntity.isAccountNoLocked(),
                authorityList);
    }

    public AuthResponse loginUser(LoginInput authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();
        AuthResponse authResponse;

        try {
            Authentication authentication = this.authenticate(username, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = jwtUtils.createToken(authentication);
            authResponse = new AuthResponse(username, "User loged succesfully", accessToken, true);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            authResponse = new AuthResponse(username, "Bad credentials", null, false);
        }
        return  authResponse;
    }

    public AuthResponse createUser(RegisterInput registerInput) {
        String username = registerInput.username();
        String password = registerInput.password();
        String roleRequest = registerInput.roleRequest()==null ? "USER" : registerInput.roleRequest();
        Role roleEntity = roleRepository.findByRoleName(RoleEnum.valueOf(roleRequest));

        if (roleEntity == null) {
            throw new IllegalArgumentException("The roles specified does not exist.");
        }
        Usuario userEntity = Usuario.builder().username(username).password(passwordEncoder.encode(password))
                .role(roleEntity).isEnabled(true).accountNoLocked(true).accountNoExpired(true).credentialNoExpired(true)
                .build();
        try {
            Usuario userSaved = userRepository.save(userEntity);
            ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority("ROLE_".concat(userEntity.getRole().getRoleName().toString())));
            userEntity.getRole().getPermissions()
                    .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

            // SecurityContext securityContextHolder = SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved, null, authorities);
            String accessToken = jwtUtils.createToken(authentication);
            AuthResponse authResponse = new AuthResponse(username, "User created successfully", accessToken, true);
            return authResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return new AuthResponse(username, "Error creating user", null, false);
        }
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException(String.format("Invalid username or password."));
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password.");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }
}

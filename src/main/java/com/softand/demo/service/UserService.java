package com.softand.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softand.demo.models.PermissionEntity;
import com.softand.demo.models.Role;
import com.softand.demo.models.RoleEnum;
import com.softand.demo.models.Usuario;
import com.softand.demo.repositories.PermissionRepository;
import com.softand.demo.repositories.RoleRepository;
import com.softand.demo.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PermissionRepository permissionRepository;

    
    public List<Usuario> getAllUsers() {
        return userRepository.findAll();
    }

    // public UserEntity createUser(String username, String password) {
    public Usuario createUser(Usuario user) {
        
        String username = user.getUsername();
        String password = user.getPassword();

        if (username == null || username.isEmpty()) {
            throw new RuntimeException("Username cannot be null or empty");
        }
        
        user.setUsername(username);
        user.setPassword(password);
        
        user.setEnabled(true);
        user.setAccountNoExpired(true);
        user.setAccountNoLocked(true);
        user.setCredentialNoExpired(true);

        // Asignar el rol USER por defecto
        Role userRole = roleRepository.findByRoleName(RoleEnum.USER);

        user.setRole(userRole);

        return userRepository.save(user);
    }

    public void assignRolesToUser(String userId, String roleId) {
        Optional<Usuario> userOpt = userRepository.findById(userId);
        Optional<Role> roleOpt = roleRepository.findById(roleId);

        if (userOpt.isPresent() && roleOpt.isPresent()) {
            Usuario user = userOpt.get();
            Role role = roleOpt.get();

            user.setRole(role);

            userRepository.save(user);
        }
    }

    public void assignPermissionsToRole(String roleId, String permissionId) {
        Optional<Role> roleOpt = roleRepository.findById(roleId);
        Optional<PermissionEntity> permissionOpt = permissionRepository.findById(permissionId);

        if (roleOpt.isPresent() && permissionOpt.isPresent()) {
            Role role = roleOpt.get();
            role.getPermissions().add(permissionOpt.get());

            roleRepository.save(role);
        }
    }

}

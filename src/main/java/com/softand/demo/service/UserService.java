package com.softand.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softand.demo.persistence.entity.PermissionEntity;
import com.softand.demo.persistence.entity.RoleEntity;
import com.softand.demo.persistence.entity.RoleEnum;
import com.softand.demo.persistence.entity.UserEntity;
import com.softand.demo.persistence.repository.PermissionRepository;
import com.softand.demo.persistence.repository.RoleRepository;
import com.softand.demo.persistence.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PermissionRepository permissionRepository;

    
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // public UserEntity createUser(String username, String password) {
    public UserEntity createUser(UserEntity user) {
        
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
        RoleEntity userRole = roleRepository.findByRoleName(RoleEnum.USER);

        user.setRoles(new HashSet<>());
        user.getRoles().add(userRole);

        return userRepository.save(user);
    }

    public void assignRolesToUser(String userId, String roleId) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        Optional<RoleEntity> roleOpt = roleRepository.findById(roleId);

        if (userOpt.isPresent() && roleOpt.isPresent()) {
            UserEntity user = userOpt.get();
            RoleEntity role = roleOpt.get();

            user.getRoles().add(role);

            userRepository.save(user);
        }
    }

    public void assignPermissionsToRole(String roleId, String permissionId) {
        Optional<RoleEntity> roleOpt = roleRepository.findById(roleId);
        Optional<PermissionEntity> permissionOpt = permissionRepository.findById(permissionId);

        if (roleOpt.isPresent() && permissionOpt.isPresent()) {
            RoleEntity role = roleOpt.get();
            role.getPermissions().add(permissionOpt.get());

            roleRepository.save(role);
        }
    }

}

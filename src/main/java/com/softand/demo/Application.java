package com.softand.demo;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.softand.demo.models.PermissionEntity;
import com.softand.demo.models.Role;
import com.softand.demo.models.RoleEnum;
import com.softand.demo.models.Usuario;
import com.softand.demo.repositories.PermissionRepository;
import com.softand.demo.repositories.RoleRepository;
import com.softand.demo.repositories.UserRepository;

@SpringBootApplication()
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository,
			RoleRepository roleRepository) {
		return args -> {

			// Create PERMISSIONS
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();
			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();
			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();
			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();
			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR")
					.build();
			if (permissionRepository.count() == 0) {
				permissionRepository.saveAll(List.of(createPermission, readPermission, updatePermission,
						deletePermission, refactorPermission));
			}

			/* Create ROLES */
			Role roleAdmin = Role.builder()
					.roleName(RoleEnum.ADMIN)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			Role roleUser = Role.builder()
					.roleName(RoleEnum.USER)
					.permissions(Set.of(createPermission, readPermission))
					.build();

			Role roleInvited = Role.builder()
					.roleName(RoleEnum.INVITED)
					.permissions(Set.of(readPermission))
					.build();
			Role roleDeveloper = Role.builder()
					.roleName(RoleEnum.DEVELOPER)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission,
							refactorPermission))
					.build();

			if (roleRepository.count() == 0) {
				roleRepository.saveAll(List.of(roleAdmin, roleUser, roleDeveloper, roleInvited, roleDeveloper));
			}

			// Create Users
			Usuario userAndres = Usuario.builder()
					.username("andres")
					.password("$2a$10$KWdxekR.JGGFhArIDyopUugeN1erXS/ceggSJyzrPcHnqXxDz4yqG")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.role(roleAdmin)
					.build();
			Usuario userDaniel = Usuario.builder()
					.username("daniel")
					.password("$2a$10$KWdxekR.JGGFhArIDyopUugeN1erXS/ceggSJyzrPcHnqXxDz4yqG")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.role(roleUser)
					.build();
			Usuario userCarla = Usuario.builder()
					.username("carla")
					.password("$2a$10$KWdxekR.JGGFhArIDyopUugeN1erXS/ceggSJyzrPcHnqXxDz4yqG")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.role(roleInvited)
					.build();
			Usuario userAngy = Usuario.builder()
					.username("anyi")
					.password("$2a$10$KWdxekR.JGGFhArIDyopUugeN1erXS/ceggSJyzrPcHnqXxDz4yqG")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.role(roleDeveloper)
					.build();
			if (userRepository.count() == 0) {
				userRepository.saveAll(List.of(userAndres, userDaniel, userCarla, userAngy));
			}
		};
	}

}

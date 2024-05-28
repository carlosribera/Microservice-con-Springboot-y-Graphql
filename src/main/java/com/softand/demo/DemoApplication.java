package com.softand.demo;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.softand.demo.persistence.entity.PermissionEntity;
import com.softand.demo.persistence.entity.RoleEntity;
import com.softand.demo.persistence.entity.RoleEnum;
import com.softand.demo.persistence.entity.UserEntity;
import com.softand.demo.persistence.repository.PermissionRepository;
import com.softand.demo.persistence.repository.RoleRepository;
import com.softand.demo.persistence.repository.UserRepository;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableMongoRepositories(basePackages = "com.softand.demo.persistence.repository")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
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
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleName(RoleEnum.ADMIN)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleName(RoleEnum.USER)
					.permissions(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity.builder()
					.roleName(RoleEnum.INVITED)
					.permissions(Set.of(readPermission))
					.build();
			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleName(RoleEnum.DEVELOPER)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission,
							refactorPermission))
					.build();

			if (roleRepository.count() == 0) {
				roleRepository.saveAll(List.of(roleAdmin, roleUser, roleDeveloper, roleInvited, roleDeveloper));
			}

			// Create Users
			UserEntity userAndres = UserEntity.builder()
					.username("andres")
					.password("$2a$10$KWdxekR.JGGFhArIDyopUugeN1erXS/ceggSJyzrPcHnqXxDz4yqG")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();
			UserEntity userDaniel = UserEntity.builder()
					.username("daniel")
					.password("$2a$10$KWdxekR.JGGFhArIDyopUugeN1erXS/ceggSJyzrPcHnqXxDz4yqG")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();
			UserEntity userCarla = UserEntity.builder()
					.username("carla")
					.password("$2a$10$KWdxekR.JGGFhArIDyopUugeN1erXS/ceggSJyzrPcHnqXxDz4yqG")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();
			UserEntity userAngy = UserEntity.builder()
					.username("anyi")
					.password("$2a$10$KWdxekR.JGGFhArIDyopUugeN1erXS/ceggSJyzrPcHnqXxDz4yqG")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();
			if (userRepository.count() == 0) {
				userRepository.saveAll(List.of(userAndres, userDaniel, userCarla, userAngy));
			}
		};
	}

}

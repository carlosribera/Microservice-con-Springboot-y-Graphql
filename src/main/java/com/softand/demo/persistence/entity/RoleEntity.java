package com.softand.demo.persistence.entity;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Entity;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Document(collection = "roles")
public class RoleEntity {

    @Id
    private String id;
    @Field("role_name")
    @Indexed(unique = true)
    private RoleEnum roleName;
    
    @Field("permissions")
    private Set<PermissionEntity> permissions;
}

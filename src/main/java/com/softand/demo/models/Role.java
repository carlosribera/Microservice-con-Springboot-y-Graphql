package com.softand.demo.models;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "roles")
public class Role {

    @Id
    private String id;
    @Field("role_name")
    @Indexed(unique = true)
    private RoleEnum roleName;
    
    @Field("permissions")
    private Set<PermissionEntity> permissions;
}

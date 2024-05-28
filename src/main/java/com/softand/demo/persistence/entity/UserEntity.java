package com.softand.demo.persistence.entity;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;
    private String username;
    private String password;

    @Field("is_enabled")
    private boolean isEnabled;
    @Field("account_No_Expired")
    private boolean accountNoExpired;
    @Field("account_No_Locked")
    private boolean accountNoLocked;
    @Field("credential_No_Expired")
    private boolean credentialNoExpired;

    private Set<RoleEntity> roles;
}

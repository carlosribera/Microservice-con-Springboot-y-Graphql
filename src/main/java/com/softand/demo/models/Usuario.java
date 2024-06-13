package com.softand.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class Usuario {

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

    private Role role;
}

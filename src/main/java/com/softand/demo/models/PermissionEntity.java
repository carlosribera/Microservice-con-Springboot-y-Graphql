package com.softand.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "permissions")
public class PermissionEntity {
    
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;
}

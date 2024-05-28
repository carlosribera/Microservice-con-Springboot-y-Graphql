package com.softand.demo.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Document(collection = "permissions")
public class PermissionEntity {
    
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;
}

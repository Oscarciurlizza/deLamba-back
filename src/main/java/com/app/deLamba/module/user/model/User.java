package com.app.deLamba.module.user.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.app.deLamba.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "users")
public class User extends BaseEntity {
    private String name;
    
    @Indexed(unique = true)
    private String email;
    
    private String password;
    private UserRole role;
    private String address;
    private String phone;
}

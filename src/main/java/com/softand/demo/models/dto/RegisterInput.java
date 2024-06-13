package com.softand.demo.models.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterInput(@NotBlank String username, 
                            @NotBlank String password,
                            String roleRequest) {

}

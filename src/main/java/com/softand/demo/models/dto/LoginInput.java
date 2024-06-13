package com.softand.demo.models.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginInput( @NotBlank String username, 
                                @NotBlank String password) {

}

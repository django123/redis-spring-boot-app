package com.django.redis_spring_boot_app.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserEntityDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    private Long followers;

}

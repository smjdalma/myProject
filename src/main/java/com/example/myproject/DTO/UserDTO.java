package com.example.myproject.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class UserDTO {
    public String num;
    @NotNull
    @NotEmpty
    public String name;
    @NotNull
    @NotEmpty
    public String add;
}

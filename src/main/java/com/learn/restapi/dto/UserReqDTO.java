package com.learn.restapi.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class UserReqDTO {

    @NotEmpty (message = "Username wajib diisi")
    @Length (min = 3, message = "Username minimal 3 karakter")
    @Length (max = 50, message = "Username maksimal 50 karakter")
    private String username;

    @NotNull (message = "Saldo produk wajib diisi.")
    @PositiveOrZero
    private Integer saldoUser;
}

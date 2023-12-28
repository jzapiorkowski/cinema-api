package com.cinema.cinema.user.dto;

import com.cinema.cinema.user.controllers.UsersRoleOutputDto;
import lombok.Data;

import java.util.List;

@Data
public class UserOutputDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<UsersRoleOutputDto> roles;
    private List<UsersReservationOutputDto> reservations;
}

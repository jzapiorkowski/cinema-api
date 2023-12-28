package com.cinema.cinema.roles.models;

import com.cinema.cinema.user.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}

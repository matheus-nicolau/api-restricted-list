package com.api.restrictedlist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "tb_tracked_user")
public class TrackedUserModel implements Serializable {

    @Id
    @Column(nullable = false, unique = true, length = 11)
    private String userCpf;

    private LocalDateTime registrationDate;
}

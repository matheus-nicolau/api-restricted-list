package com.api.restrictedlist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrackedUserDTO {

    @NotBlank
    @Size(max = 11)
    private String userCpf;

}

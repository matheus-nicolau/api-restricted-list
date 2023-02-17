package com.api.restrictedlist;

import com.api.restrictedlist.constants.AllConstants;
import com.api.restrictedlist.controller.TrackedUserController;
import com.api.restrictedlist.dto.TrackedUserDTO;
import com.api.restrictedlist.model.TrackedUserModel;
import com.api.restrictedlist.repository.TrackedUserRepository;
import com.api.restrictedlist.service.TrackedUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class TrackedUserServiceTest {


    @InjectMocks
    private TrackedUserService trackedUserService;

    @Mock
    TrackedUserRepository trackedUserRepository;

    @Mock
    AllConstants allConstants;

    private TrackedUserDTO trackedUserDTOFull;
    private TrackedUserDTO trackedUserDTOClean;
    @BeforeEach
    void setup() {
        trackedUserDTOFull = new TrackedUserDTO();
        trackedUserDTOFull.setCpf("219.595.280-68");

        trackedUserDTOClean = new TrackedUserDTO();
        trackedUserDTOClean.setCpf("21959528068");


    }

    @Test
    void mustValidateTheCpf(){
      assertDoesNotThrow(() -> trackedUserService.cpfValidator(trackedUserDTOClean.getCpf()));
    }

}

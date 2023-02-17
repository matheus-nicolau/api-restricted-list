package com.api.restrictedlist;

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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrackedUserControllerTest {


    @InjectMocks
    private TrackedUserController trackedUserController;
    @Mock
    TrackedUserService trackedUserService;


    @MockBean
    TrackedUserRepository trackedUserRepository;

    private TrackedUserDTO trackedUserDTO;
    private TrackedUserModel trackedUserModel;

    @BeforeEach
    void setup() {
        trackedUserDTO = new TrackedUserDTO();
        trackedUserDTO.setCpf("219.595.280-68");

        trackedUserModel = new TrackedUserModel();
        trackedUserModel.setCpf("21959528068");
        trackedUserModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC-0300")));
    }

    @Test
    void addValidCpfOnList(){
        var response = assertDoesNotThrow(() -> trackedUserController.saveTrackedUser(trackedUserDTO));
        assertNotNull(response);
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(trackedUserService.save(trackedUserDTO)), response);
    }

    @Test
    void mustReturnList(){
        ResponseEntity<List<TrackedUserModel>> response = trackedUserController.findAllTrackedUser();
        assertNotNull(response);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(trackedUserService.findAll()), response);
    }
    @Test
    void mustReturnRecord(){
        var response = assertDoesNotThrow(() -> trackedUserController.findOneTrackedUser(trackedUserDTO.getCpf()));
        assertNotNull(response);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(trackedUserService.findByCpf(trackedUserDTO.getCpf())), response);
    }
//    @Test
//    void removeCpfOnList(){
//        Mockito.when(trackedUserRepository.findByCpf(trackedUserModel.getCpf()))
//                .thenReturn(Optional.of(trackedUserModel));
//
//        var response =  assertDoesNotThrow(() -> trackedUserController.deleteTrackedUser(trackedUserDTO.getCpf()));
//        assertNotNull(response);
//        assertEquals(ResponseEntity.status(HttpStatus.OK).body("CPF deleted successfully."), response);

//        Optional<TrackedUserModel> trackedUserModelOptional = trackedUserRepositoryu.findByCpf(trackedUserModel.getCpf());
//    }

}

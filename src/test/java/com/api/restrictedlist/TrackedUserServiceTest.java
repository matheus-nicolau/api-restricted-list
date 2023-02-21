package com.api.restrictedlist;

import com.api.restrictedlist.dto.TrackedUserDTO;
import com.api.restrictedlist.model.TrackedUserModel;
import com.api.restrictedlist.repository.TrackedUserRepository;
import com.api.restrictedlist.service.TrackedUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TrackedUserServiceTest {

    @Autowired
    TrackedUserService trackedUserService;
    @MockBean
    TrackedUserRepository trackedUserRepository;

    @Test
    @DisplayName("Must validate a CPF")
    public void testCpfValidator() {
        var trackedUserDTO = new TrackedUserDTO();
        trackedUserDTO.setCpf("33077258072");

        assertDoesNotThrow(() -> trackedUserService.cpfValidator(trackedUserDTO.getCpf()));
    }

    @Test
    @DisplayName("Must validate if CPF already exists")
    public void testCpfAlreadyExists() {
        var trackedUserModel = new TrackedUserModel();
        trackedUserModel.setCpf("3307725872");

        Mockito.when(trackedUserRepository.findByCpf(trackedUserModel.getCpf()))
                .thenReturn(Optional.of(trackedUserModel));

        var cpfFormat = "59727332005";
        assertDoesNotThrow(() -> trackedUserService.cpfAlreadyExists(cpfFormat));
    }

    @Test
    @DisplayName("Must clear a CPF")
    public void testClearCpf() {
        var cpfNotFormat = "597.273.320-05";
        var cpfFormat = "59727332005";
        var response = assertDoesNotThrow(() -> trackedUserService.clearCpf(cpfNotFormat));
        assertEquals(cpfFormat , response);
    }

    @Test
    @DisplayName("Must return a model")
    public void testFindByCpf() {
        var trackedUserModel = new TrackedUserModel();
        trackedUserModel.setCpf("02905783028");

        Mockito.when(trackedUserRepository.findByCpf(trackedUserModel.getCpf()))
                .thenReturn(Optional.of(trackedUserModel));
        var cpfFormat = "02905783028";
        var response =  assertDoesNotThrow(() -> trackedUserService.findByCpf(cpfFormat));
        assertEquals(trackedUserModel, response);
    }

    @Test
    @DisplayName("Must return a list of model")
    public void testFindAll() {

        var reg1 = new TrackedUserModel();
        var reg2 = new TrackedUserModel();
        var reg3 = new TrackedUserModel();
       List<TrackedUserModel> listCompare = new ArrayList<>();
       listCompare.add(reg1);
       listCompare.add(reg2);
       listCompare.add(reg3);

        Mockito.when(trackedUserRepository.findAll()).thenReturn(listCompare);

        var listResponse = assertDoesNotThrow(() -> trackedUserService.findAll());
        assertEquals(listCompare, listResponse);
    }

    @Test
    @DisplayName("Must delete the model")
    public void testDeleteCpf() {
        var trackedUserModel = new TrackedUserModel();
        trackedUserModel.setCpf("02905783028");
        assertDoesNotThrow(() -> trackedUserService.delete(trackedUserModel));
    }

    @Test
    @DisplayName("Must save and transfer DTO to Model")
    public void testSaveCpf() {
        var trackedUserDTO = new TrackedUserDTO();
        trackedUserDTO.setCpf("02905783028");
        var response = assertDoesNotThrow(() -> trackedUserService.save(trackedUserDTO));
        System.out.println("cpf: " + response.getCpf() + "\ncreatedAt: " + response.getCreatedAt());
    }

}

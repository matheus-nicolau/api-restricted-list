package com.api.restrictedlist;

import com.api.restrictedlist.dto.TrackedUserDTO;
import com.api.restrictedlist.model.TrackedUserModel;
import com.api.restrictedlist.service.TrackedUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TrackedUserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    TrackedUserService trackedUserService;

    @Test
    @DisplayName("Test to save a tracked user")
    void trackedUserTestSave() throws Exception {

        var trackedUserDTO = new TrackedUserDTO();
        trackedUserDTO.setCpf("15499304009");

        mockMvc.perform(post("/cpf")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(trackedUserDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("Test to get list of tracked users")
    void trackedTestGetAll() throws Exception {
        mockMvc.perform(get("/cpf"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    @DisplayName("Test to get a tracked user")
    void trackedUserTestGetOne() throws Exception {

        mockMvc.perform(get("/cpf/0278279819"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    @DisplayName("Test to remove a tracked user")
    void trackedUserTestRemove() throws Exception {

        var trackedUserModel= new TrackedUserModel();
        trackedUserModel.setCpf("63893633030");

        Mockito.when(trackedUserService.findByCpf(Mockito.any())).thenReturn(trackedUserModel);

        mockMvc.perform(delete("/cpf/63893633030")
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

}

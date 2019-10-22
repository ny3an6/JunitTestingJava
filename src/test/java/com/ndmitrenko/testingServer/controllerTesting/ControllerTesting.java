package com.ndmitrenko.testingServer.controllerTesting;

import com.ndmitrenko.testingServer.controller.Controller;
import com.ndmitrenko.testingServer.model.Company;
import com.ndmitrenko.testingServer.model.User;
import com.ndmitrenko.testingServer.repository.CompanyRepository;
import com.ndmitrenko.testingServer.repository.UserRepository;
import com.ndmitrenko.testingServer.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerTesting {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CompanyRepository companyRepository;

    @Test
    public void testController() throws Exception {
        this.mockMvc.perform(get("/company/1136/users?name=Kate").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[1].username").doesNotExist())
                .andExpect(jsonPath("$.[2].company").doesNotExist()).andReturn();
        verify(userService, times(1)).getUser(anyLong(), anyString());
    }
}

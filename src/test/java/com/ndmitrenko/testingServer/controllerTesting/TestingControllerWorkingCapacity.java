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
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class TestingControllerWorkingCapacity {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CompanyRepository companyRepository;

    private static final Long PATH_VARIABLE = 1136L;

    private static final String USER_NAME = "Kate";

    @Test
    public void testController() throws Exception {
        List<User> users = userRepository.findUserByCompany_CompanyIdAndUsername(anyLong(), anyString());
        Company company = new Company(2L,"Iron Skill", 1136L, users);
        when(userService.getUser(anyLong(), anyString())).thenReturn(Collections.singletonList(new User(3L,"Kate", company)));
        this.mockMvc.perform(get("/company/{companyId}/users?name=Kate", 1136L).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(3L))
                .andExpect(jsonPath("$.[0].username").value(USER_NAME))
                .andExpect(jsonPath("$.[0].company.id").value(2L))
                .andExpect(jsonPath("$.[0].company.companyName").value(company.getCompanyName()))
                .andExpect(jsonPath("$.[0].company.companyId").value(PATH_VARIABLE))
                .andReturn();
        verify(userService, times(1)).getUser(anyLong(), anyString());
    }
}

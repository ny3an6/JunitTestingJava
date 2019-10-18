package com.ndmitrenko.testingServer.service;


import com.ndmitrenko.testingServer.TestingServer;
import com.ndmitrenko.testingServer.controller.Controller;
import com.ndmitrenko.testingServer.model.Company;
import com.ndmitrenko.testingServer.model.User;
import com.ndmitrenko.testingServer.repository.CompanyRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.script.ScriptException;

import java.sql.SQLException;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestingServer.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ContextConfiguration(classes=TestingServer.class)
public class UserServiceTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyRepository userRepository;

    @Test
    public void getUser() throws Exception {
        System.out.println(userService.getUser(1136L, "Kate"));
        User userasd = new User();
        Company company = new Company();
        userasd.setUsername("Kate");
        userasd.setCompany(company);
        userRepository.save(userasd);
        User user  = new User();
        user.setUsername("Kate");
        given(userService.getUser(1136L, "Kate").getUsername()).willReturn(user.getUsername());

    }
}



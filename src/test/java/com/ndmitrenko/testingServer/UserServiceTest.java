package com.ndmitrenko.testingServer;


import com.ndmitrenko.testingServer.model.User;
import com.ndmitrenko.testingServer.repository.CompanyRepository;
import com.ndmitrenko.testingServer.repository.UserRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getUser() throws Exception {
        System.out.println(companyRepository.findAll());
        System.out.println(userService.getUser(1136L, "Kate"));
        User user  = new User();
        user.setUsername("Kate");
        given(userService.getUser(1136L, "Kate").getUsername()).willReturn(user.getUsername());
    }
}



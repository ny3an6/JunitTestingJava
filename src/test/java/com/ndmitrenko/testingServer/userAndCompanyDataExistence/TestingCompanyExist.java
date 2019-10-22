package com.ndmitrenko.testingServer.userAndCompanyDataExistence;


import com.ndmitrenko.testingServer.exception.DefaultException;
import com.ndmitrenko.testingServer.model.User;
import com.ndmitrenko.testingServer.repository.CompanyRepository;
import com.ndmitrenko.testingServer.repository.UserRepository;
import com.ndmitrenko.testingServer.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestingCompanyExist {

    private static final Long INCORRECT_COMPANY_ID = 113L;

    private static final String USER_NAME = "Alex";

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void getUser(){
        try{
            userService.getUser(INCORRECT_COMPANY_ID, USER_NAME);
            Assert.fail("Expected DefaultException");
        }catch (DefaultException ex){
            verify(userRepository, times(0)).findUserByCompany_CompanyIdAndUsername(anyLong(), anyString());
            Assert.assertEquals(ex.getMessage(), "Сompany with "+INCORRECT_COMPANY_ID+ " id " +"does't exist");
            Assert.assertEquals(ex.getCode(), HttpStatus.NOT_FOUND);
        }
    }
}



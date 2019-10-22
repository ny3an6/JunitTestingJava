package com.ndmitrenko.testingServer.userAndCompanyDataExistence;

import com.ndmitrenko.testingServer.exception.DefaultException;
import com.ndmitrenko.testingServer.repository.CompanyRepository;
import com.ndmitrenko.testingServer.repository.UserRepository;
import com.ndmitrenko.testingServer.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestingUserExist {

    private static final Long COMPANY_ID = 1136L;

    private static final String INCORRECT_USER_NAME = "Nikita";

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void getUser() {
        try {
            userService.getUser(COMPANY_ID, INCORRECT_USER_NAME);
            Assert.fail("Expected DefaultException");
        } catch (DefaultException de) {
            verify(userRepository, times(1)).findUserByCompany_CompanyIdAndUsername(anyLong(), anyString());
            Assert.assertEquals(de.getMessage(), "User not found");
            Assert.assertEquals(de.getCode(), HttpStatus.NOT_FOUND);
        }
    }
}

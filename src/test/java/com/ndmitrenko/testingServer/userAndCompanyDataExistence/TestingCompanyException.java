package com.ndmitrenko.testingServer.userAndCompanyDataExistence;


import com.ndmitrenko.testingServer.exception.ApiResult;
import com.ndmitrenko.testingServer.exception.DefaultException;
import com.ndmitrenko.testingServer.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TestingCompanyException {

    private static final Long INCORRECT_COMPANY_ID = 112L;

    private static final String USER_NAME = "Alex";

    @Autowired
    private UserService userService;

    @Test
    public void getUser(){
        try{
            userService.getUser(INCORRECT_COMPANY_ID, USER_NAME);
            Assert.fail("Expected DefaultException");
        }catch (DefaultException ex){
            Assert.assertEquals(ex.getMessage(), "Сompany with "+INCORRECT_COMPANY_ID+ " id " +"does't exist");
            Assert.assertEquals(ex.getCode(), HttpStatus.NOT_FOUND);
            Assert.assertEquals(ex.getApiResult(), ApiResult.FAIL);
        }
    }
}



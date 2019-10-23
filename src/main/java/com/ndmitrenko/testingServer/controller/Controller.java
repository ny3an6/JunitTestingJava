package com.ndmitrenko.testingServer.controller;

import com.ndmitrenko.testingServer.model.User;
import com.ndmitrenko.testingServer.repository.CompanyRepository;
import com.ndmitrenko.testingServer.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class Controller {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyRepository companyRepository;

    @ApiOperation(value = "Show users of his own company")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Failed to show")
    })
    @GetMapping("/{companyId}/users")
    @ResponseBody
    public List<User> show(@PathVariable String companyId, @RequestParam(value = "name", required = false) String name) {
        return userService.getUser(Long.parseLong(companyId), name);
    }
}

package com.ndmitrenko.testingServer.controller;

import com.ndmitrenko.testingServer.model.Company;
import com.ndmitrenko.testingServer.model.User;
import com.ndmitrenko.testingServer.repository.CompanyRepository;
import com.ndmitrenko.testingServer.service.UserService;
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

    @GetMapping("/{companyId}/users")
    @ResponseBody
    public List<User> show(@PathVariable String companyId, @RequestParam(value = "name", required = false) String name) {
        return userService.getUser(Long.parseLong(companyId), name);
    }

    @GetMapping("/companies")
    public List<Company> shows(){
        return companyRepository.findAll();
    }
}

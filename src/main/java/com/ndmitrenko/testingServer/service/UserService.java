package com.ndmitrenko.testingServer.service;

import com.ndmitrenko.testingServer.exception.DefaultException;
import com.ndmitrenko.testingServer.model.Company;
import com.ndmitrenko.testingServer.model.User;
import com.ndmitrenko.testingServer.repository.CompanyRepository;
import com.ndmitrenko.testingServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public List<User> getUser(Long companyId, String userName) {
        Company company = companyRepository.findByCompanyId(companyId);
        if (company != null) {
            List<User> user = userRepository.findUserByCompany_CompanyIdAndUsername(companyId, userName);
            if (!user.isEmpty()) {
                System.out.println(user);
                return user;
            } else throw new DefaultException(HttpStatus.NOT_FOUND, "User not found");
        }else throw new DefaultException(HttpStatus.NOT_FOUND, "Сompany with "+companyId+" id " +"does't exist");
    }
}


package com.ndmitrenko.testingServer.service;

import com.ndmitrenko.testingServer.exception.DefaultException;
import com.ndmitrenko.testingServer.model.Company;
import com.ndmitrenko.testingServer.model.User;
import com.ndmitrenko.testingServer.repository.CompanyRepository;
import com.ndmitrenko.testingServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public User getUser(Long companyId, String userName) {
           Company company = companyRepository.findByCompanyId(companyId);
           if (company != null) {
               User user = null;
               for (User userCandidate : company.getUser()) {
                   System.out.println(userCandidate.getUsername());
                   if (userCandidate.getUsername().equals(userName)) {
                       user = userRepository.findByUsername(userName);
                       break;
                   } else throw new DefaultException(HttpStatus.NOT_FOUND, "User not found");
               }
               return user;
           }else {
               throw new DefaultException(HttpStatus.NOT_FOUND, "No company founded");
           }
     }

 /*   public User getUser2(Long companyId, String userName) {
        Company company = companyRepository.findByCompanyId(companyId);
        if (company != null) {
            User user = null;
                company.getUser().stream().filter()
                if (userCandidate.getUsername().equals(userName)) {
                    user = userRepository.findByUsername(userName);
                    break;
                } else throw new DefaultException(HttpStatus.NOT_FOUND, "User not found");
            }
            return user;
        }else {
            throw new DefaultException(HttpStatus.NOT_FOUND, "No company founded");
        }
    }*/
}


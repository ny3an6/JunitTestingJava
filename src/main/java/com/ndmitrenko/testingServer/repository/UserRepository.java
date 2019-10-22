package com.ndmitrenko.testingServer.repository;

import com.ndmitrenko.testingServer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByCompany_CompanyIdAndUsername(Long companyId, String userName);
}

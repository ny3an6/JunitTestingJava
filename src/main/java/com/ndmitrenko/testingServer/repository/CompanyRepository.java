package com.ndmitrenko.testingServer.repository;

import com.ndmitrenko.testingServer.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByCompanyId(Long companyId);
}

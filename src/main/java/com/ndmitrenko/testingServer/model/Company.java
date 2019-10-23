package com.ndmitrenko.testingServer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")
@ToString(exclude = "user")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "company_id")
    private Long companyId;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "company", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> user;

}

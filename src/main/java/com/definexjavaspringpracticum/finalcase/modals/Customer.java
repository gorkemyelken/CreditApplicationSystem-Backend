package com.definexjavaspringpracticum.finalcase.modals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name= "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","creditApplications"})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String identityNumber;

    private String firstName;

    private String lastName;

    private Double monthlyIncome;

    private String phoneNumber;

    private Date birthDate;

    private int creditScore;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<CreditApplication> creditApplications;

    public Customer(String identityNumber, String firstName, String lastName, Double monthlyIncome, String phoneNumber, Date birthDate, int creditScore) {
        this.identityNumber = identityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.monthlyIncome = monthlyIncome;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.creditScore = creditScore;
    }
}

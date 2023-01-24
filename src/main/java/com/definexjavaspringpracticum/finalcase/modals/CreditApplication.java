package com.definexjavaspringpracticum.finalcase.modals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "credit_application")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditApplicationId;

    private String confirmationInformation; // Approved or Rejected

    private Double limit;

    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    public CreditApplication(Date createDate) {
        this.createDate = createDate;
    }
}

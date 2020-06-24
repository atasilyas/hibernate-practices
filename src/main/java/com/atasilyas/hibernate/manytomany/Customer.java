package com.atasilyas.hibernate.manytomany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "customer_no", nullable =  false)
    private String customerNo;
}

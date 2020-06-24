package com.atasilyas.hibernate.onetoone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;
}

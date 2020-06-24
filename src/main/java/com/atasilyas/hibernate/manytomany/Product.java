package com.atasilyas.hibernate.manytomany;

import com.atasilyas.hibernate.onetoone.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "customer_product" , joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private List<Customer> customer = new ArrayList<Customer>();
}

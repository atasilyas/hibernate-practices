package com.atasilyas.hibernate.manytoone;

import com.atasilyas.hibernate.onetoone.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "identity_card")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "card_type")
    private String cardType;

    @ManyToOne(cascade = CascadeType.ALL)//eger idnetity save edilirken üstündeki user da save edilmek istenirse o zaman casdcade kullan
    @JoinColumn(name = "customer_id")
    private Customer customer ;
}

package com.atasilyas.hibernate.onetoone;

import com.atasilyas.hibernate.manytomany.Product;
import com.atasilyas.hibernate.manytoone.IdentityCard;
import com.atasilyas.hibernate.onetomany.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


    @OneToMany(cascade = CascadeType.ALL)// eger bunu koymazsak ve customer içerisine address setlersek object references an unsaved transient instance - save the transient instance before flushing
    @JoinTable( name = "customer_Address" ,joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addresses =  new ArrayList<Address>();

    @OneToMany(mappedBy ="customer",cascade = CascadeType.ALL)//casdcade kullan eger customer üzerinde bir transient ıdentity card varsa yani save edilmek istenen bir card varsa all olmalı.
    private List<IdentityCard> identityCard =  new ArrayList<IdentityCard>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")//mapped by sayesinde 1 tablo eksilir toplam 3 olur artık product içerisindeki customer tablo yaratmamktan sorumlu.
    private List<Product> products = new ArrayList<Product>();
}

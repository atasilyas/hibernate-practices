package com.atasilyas.hibernate.onetoone;


import com.atasilyas.hibernate.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @OneToOne(mappedBy = "role") // burada casdcade yok ve diyelim ki role ustunde bir transient customer
    // objesi var bu sekilde oldugunda eget biz bu role yi save edersek hata almaz ve customer save edilmez.
    // Casdcade koyarsak eget o zaman customer tablosunda olusur bu customer. Ama bu seferde customer de role_id alanı bos olur.
    private Customer user;
}

package com.atasilyas.hibernate.entity;

import com.atasilyas.hibernate.eagerandlazyloading.Contact;
import com.atasilyas.hibernate.enbeddedobject.Address;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_table")
@DynamicInsert
@DynamicUpdate
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name", length = 100, nullable = false)
    private String userName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "salary")
    private Double salary;

    @Embedded
    @AttributeOverrides(value = {

            @AttributeOverride(column = @Column(name = "home_street", length = 50), name = "street"),
            @AttributeOverride(column = @Column(name = "home_city", length = 50), name = "city"),
            @AttributeOverride(column = @Column(name = "home_pin_code", length = 5), name = "pinCode")
    })
    private Address homeAddress;


    @Embedded
    @AttributeOverrides(value = {

            @AttributeOverride(column = @Column(name = "office_street", length = 50), name = "street"),
            @AttributeOverride(column = @Column(name = "office_city", length = 50), name = "city"),
            @AttributeOverride(column = @Column(name = "office_pincode", length = 5), name = "pincode")
    })
    private Address offiiceAddress;

    //@ElementCollection(fetch = FetchType.EAGER)
    @ElementCollection(fetch = FetchType.LAZY) //default.
    @JoinTable(name = "contact_table", joinColumns = @JoinColumn(name = "employee_id"))
    @GenericGenerator(name = "gen", strategy = "sequence")
    @CollectionId(columns = {@Column(name = "contact_id")}, generator = "gen", type = @Type(type = "int"))
    List<Contact> contactList = new ArrayList();

    public Address getHomeAddress()
    {
        return homeAddress;
    }

    public List<Contact> getContactList()
    {
        return contactList;
    }

    public void setContactList(List<Contact> contactList)
    {
        this.contactList = contactList;
    }

    public void setHomeAddress(Address homeAddress)
    {
        this.homeAddress = homeAddress;
    }

    public Address getOffiiceAddress()
    {
        return offiiceAddress;
    }

    public void setOffiiceAddress(Address offiiceAddress)
    {
        this.offiiceAddress = offiiceAddress;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public Double getSalary()
    {
        return salary;
    }

    public void setSalary(Double salary)
    {
        this.salary = salary;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", createDate=" + createDate +
                ", salary=" + salary +
                '}';
    }
}

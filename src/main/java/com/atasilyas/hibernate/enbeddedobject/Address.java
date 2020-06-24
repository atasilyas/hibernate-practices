package com.atasilyas.hibernate.enbeddedobject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address
{
    @Column(name = "street", length = 50)
    private String street;
    @Column(name = "city", length = 50)
    private String city;
    @Column(name = "pincode", length = 5)
    private Long pinCode;

    public Address()
    {
    }

    public Address(String street, String city, Long pinCode)
    {
        this.street = street;
        this.city = city;
        this.pinCode = pinCode;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public Long getPinCode()
    {
        return pinCode;
    }

    public void setPinCode(Long pinCode)
    {
        this.pinCode = pinCode;
    }

    @Override
    public String toString()
    {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", pinCode=" + pinCode +
                '}';
    }
}
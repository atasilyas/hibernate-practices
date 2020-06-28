package com.atasilyas.hibernate.eagerandlazyloading;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Contact
{

    @Column(name = "contact_name", length = 50)
    private String name;

    public Contact(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}

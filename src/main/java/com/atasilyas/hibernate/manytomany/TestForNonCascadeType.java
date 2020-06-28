package com.atasilyas.hibernate.manytomany;

import com.atasilyas.hibernate.eagerandlazyloading.Contact;
import com.atasilyas.hibernate.manytoone.IdentityCard;
import com.atasilyas.hibernate.onetomany.Address;
import com.atasilyas.hibernate.onetoone.Customer;
import com.atasilyas.hibernate.onetoone.Role;
import com.atasilyas.hibernate.util.HibernateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;

public class TestForNonCascadeType {

    public static void main(String[] args) {

        Session session = null;
        Role role = null;
        Customer customer = null;
        IdentityCard identityCard = null;
        Address address = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();

            role = getRole();
            customer = getCustomer();
            identityCard = getIdentityCard();
            address = getAddress();

            session.save(role);
            session.save(customer);
            session.save(identityCard);
            session.save(address);
            session.getTransaction().commit();


        } catch (Exception exception) {
            exception.printStackTrace();
            if (ObjectUtils.isEmpty(session)) {
                session.close();
            }
        }
    }

    static Role getRole() {
        return Role.builder().roleName("ROLE_ADMIN").user(getCustomer().builder().customerNo("35522").build()).build();
    }

    static Address getAddress() {

        return Address.builder().discrict("test mahalle").city("test sehir").build();
    }

    static Customer getCustomer() {

        return Customer.builder().customerNo("123223").build();
    }

    static IdentityCard getIdentityCard() {

        return IdentityCard.builder().cardType("new Card").build();
    }

}



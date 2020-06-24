package com.atasilyas.hibernate.manytoone;

import com.atasilyas.hibernate.entity.User;
import com.atasilyas.hibernate.onetomany.Address;
import com.atasilyas.hibernate.onetoone.Customer;
import com.atasilyas.hibernate.onetoone.Role;
import com.atasilyas.hibernate.util.HibernateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        Customer customer = null;
        IdentityCard identityCard = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();


            customer = getCustomer();
            identityCard = getIdentity();
            session.save(customer); //burada eger casdcade all degilse transient object save exception fırlatır child save edilmedigi için

            session.save(identityCard);
            session.getTransaction().commit();

            session.get(Customer.class, 1);
            session.get(Role.class, 1);

            session.get(User.class, 1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ObjectUtils.isNotEmpty(session)) {
                session.close();
            }
        }

    }


    static Customer getCustomer() {
        Customer customer = new Customer();
        customer.setCustomerNo("customer'in kendisi");
        Role role = Role.builder().roleName("ROLE_USER").build();
        Address address = Address.builder().city("van").discrict("muradiye").build();
        Address address2 = Address.builder().city("van").discrict("muradiye").build();
        IdentityCard identityCard = IdentityCard.builder().cardType("ehliyet").build();
        IdentityCard identityCard1 = IdentityCard.builder().cardType("kimlikdwqe").build();
        customer.setIdentityCard(Arrays.asList(identityCard, identityCard1));
        customer.setAddresses(Arrays.asList(address, address2));
        customer.setRole(role);
        return customer;
    }


    static IdentityCard getIdentity() {
        Customer customer = new Customer();
        customer.setCustomerNo("identity ile gelen customer");
        customer.setRole(Role.builder().roleName("identity ile gelen role").build());
        customer.setAddresses(Arrays.asList(
                Address.builder().city("identiy ile gelen 1").discrict("dentiy ile gelen 1").build(),
                Address.builder().city("dentiy ile gelen 2").discrict("dentiy ile gelen 2").build()));
        return IdentityCard.builder().cardType("new Card").customer(customer).build();
    }

}

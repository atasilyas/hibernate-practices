package com.atasilyas.hibernate.manytomany;

import com.atasilyas.hibernate.entity.User;
import com.atasilyas.hibernate.manytoone.IdentityCard;
import com.atasilyas.hibernate.onetomany.Address;
import com.atasilyas.hibernate.onetoone.Customer;
import com.atasilyas.hibernate.onetoone.Role;
import com.atasilyas.hibernate.util.HibernateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        Customer customer = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();


            customer = getCustomer();
            session.save(customer); //burada eger casdcade all degilse transient object save exception fırlatır child save edilmedigi için

            session.save(getProduct());
            session.getTransaction().commit();



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
        Product product = getProduct();
        product.setName("customer ile gelen product");
        customer.setProducts(Arrays.asList(product, getProduct()));
        customer.setRole(role);
        return customer;
    }


    static Product getProduct() {
        Customer customer = new Customer();
        customer.setCustomerNo("product ile gelen customer");
        customer.setRole(Role.builder().roleName("product ile gelen role").build());
        customer.setAddresses(Arrays.asList(
                Address.builder().city("product ile gelen 1").discrict("product ile gelen 1").build(),
                Address.builder().city("product ile gelen 2").discrict("product ile gelen 2").build()));
        Customer customer2 = new Customer();
        customer2.setCustomerNo("product ile gelen customer");
        customer2.setRole(Role.builder().roleName("product ile gelen role").build());
        customer2.setAddresses(Arrays.asList(
                Address.builder().city("product ile gelen 1").discrict("product ile gelen 1").build(),
                Address.builder().city("product ile gelen 2").discrict("product ile gelen 2").build()));
        return Product.builder().name("apple macbook").customer(Arrays.asList(customer,customer2)).build();
    }

}

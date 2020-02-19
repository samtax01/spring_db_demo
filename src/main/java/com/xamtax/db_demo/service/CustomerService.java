package com.xamtax.db_demo.service;

import com.xamtax.db_demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void create(){
        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
    }





    public int insert(Customer customer){
        return jdbcTemplate.update("INSERT INTO customers(first_name, last_name) VALUES (?,?)", customer.getFirstName(), customer.getLastName());
    }



    public int update(Customer customer) {
        return jdbcTemplate.update("UPDATE customers SET first_name=?, last_name=? WHERE id=?", customer.getFirstName(), customer.getLastName(), customer.getId());
    }



    public int delete(int id) {
        return jdbcTemplate.update("DELETE customers WHERE id=?", id);
    }


    public List<Customer> all(){
        return jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers", new Object[] {},
                (rs, rowNum) -> Customer.builder().id(rs.getLong("id"))
                        .firstName(rs.getString("first_name"))
                        .lastName(rs.getString("last_name")).build());
    }



    public Customer find(int id){
        List<Customer> customer = jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers WHERE id = ?", new Object[] { id },
                (rs, rowNum) -> Customer.builder().id(rs.getLong("id"))
                        .firstName(rs.getString("first_name"))
                        .lastName(rs.getString("last_name")).build());
        return (customer.stream().findFirst().isPresent())? customer.stream().findFirst().get(): null;
    }
}

package com.xamtax.db_demo.controller;

import com.xamtax.db_demo.model.Customer;
import com.xamtax.db_demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;



    @PostMapping("/v1/customers")
    public int add(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return customerService.insert(firstName, lastName);
    }


    @GetMapping("/v1/customers")
    public List<Customer> all(){
        return customerService.all();
    }



    @GetMapping("/v1/customers/{id}")
    public Customer show(@PathVariable("id") int id){
        return customerService.find(id);
    }


    @PutMapping("/v1/customers/{id}")
    public int update(@PathVariable("id") int id, @RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
      // return String.format("firstName: %s, lastName: %s, id: %s--", firstName, lastName, id);
        return customerService.update(id, firstName, lastName);
    }

    @DeleteMapping("/v1/customers/{id}")
    public int update(@PathVariable("id") int id){
        return customerService.delete(id);
    }


    @GetMapping("/")
    public String index(){
        customerService.create();
        return "<h3>HELLO WORLD</h3>Rest app... table created";
    }


}

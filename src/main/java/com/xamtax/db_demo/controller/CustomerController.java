package com.xamtax.db_demo.controller;

import com.xamtax.db_demo.model.Customer;
import com.xamtax.db_demo.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("customers")
    @ApiOperation(
            value = "Get All",
            notes = "Get all customers information",
            response = Customer.class
    )
    public List<Customer> all(){
        return customerService.all();
    }



    @GetMapping("customers/{id}")
    public Customer show(@ApiParam(value = "ID value to locate Customer", required = true) @PathVariable("id") int id){
        return customerService.find(id);
    }


    @PostMapping("customers")
    public String add(@RequestBody Customer customer){
        return customerService.insert(customer) > 0? "Record Added!": "Failed";
    }


    @PutMapping("customers/{id}")
    public String update(@PathVariable("id") int id, @RequestBody Customer customer){
        customer.setId(id);
        return customerService.update(customer) > 0? "Record Updated!": "Failed";
    }


    @DeleteMapping("customers/{id}")
    public String delete(@PathVariable("id") int id){
        return customerService.delete(id) > 0? "Record Deleted!": "Failed";
    }


    @GetMapping("/")
    public String index(){
        return "<h3>HELLO WORLD</h3> Rest app...";
    }




/*

    @PutMapping("customers/{id}")
    public String update(@PathVariable("id") int id, @RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return customerService.update(id, firstName, lastName) > 0? "Record Updated!": "Failed";
    }
*/

/*
    @PostMapping("customers")
    public String add(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return customerService.insert(firstName, lastName) > 0? "Record Added!": "Failed";
    }
*/


}

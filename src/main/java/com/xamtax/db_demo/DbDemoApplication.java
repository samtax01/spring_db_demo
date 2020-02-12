package com.xamtax.db_demo;

import com.xamtax.db_demo.model.Customer;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
@RestController
public class DbDemoApplication{

	//private static final Logger log = LoggerFactory.getLogger(DbDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DbDemoApplication.class, args);
	}


	@Autowired
	JdbcTemplate jdbcTemplate;


	public void init(){
		jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
				.map(name -> name.split(" "))
				.collect(Collectors.toList());

		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
	}



	@GetMapping("/add")
	public int add(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
		// create db
		this.init();

		// insert
		return jdbcTemplate.update("INSERT INTO customers(first_name, last_name) VALUES (?,?)", firstName, lastName);
	}


	@GetMapping("/all")
	public List<Customer> all(){
		// create db
		this.init();

		// list all
		return jdbcTemplate.query(
				"SELECT id, first_name, last_name FROM customers", new Object[] {},
				(rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
		);
	}



	@GetMapping("/show/{id}")
	public List<Customer> show(@PathVariable("id") int id){
		// create db
		this.init();

		// find
		return jdbcTemplate.query(
				"SELECT id, first_name, last_name FROM customers WHERE id = ?", new Object[] { id },
				(rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
		); //.forEach(customer ->  buff.append(customer.toString()))
	}






	@GetMapping("/")
	public String index(){
		return "Available links are /add?firstName=&lastName=, /create, /all";
	}


}

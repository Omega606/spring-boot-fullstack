package com.skynet;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.skynet.customer.Customer;
import com.skynet.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository){

        return args -> {

            /*
            Customer alex = new Customer("Alex","alex@gmail.com",20);

            Customer sam = new Customer("Sam","sam@gmail.com",25);

            List<Customer> customers = List.of(alex, sam);
            customerRepository.saveAll(customers);
             */

            var faker = new Faker();
            Name name = faker.name();
            Random random = new Random();
            String firstName = name.firstName();
            String lastName = name.lastName();

            Customer customer = new Customer(
                    firstName +" "+ lastName,
                    firstName.toLowerCase()+"."+ lastName.toLowerCase()+"@skynet.com",
                    random.nextInt(16,99));

            customerRepository.save(customer);
        };
    }
}

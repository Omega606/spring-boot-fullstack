package com.skynet;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.skynet.customer.Customer;
import com.skynet.customer.CustomerRepository;
import com.skynet.customer.Gender;
import com.skynet.s3.S3Buckets;
import com.skynet.s3.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository, PasswordEncoder passwordEncoder){

        return args -> {

            /*
            Customer alex = new Customer("Alex","alex@gmail.com",20);

            Customer sam = new Customer("Sam","sam@gmail.com",25);

            List<Customer> customers = List.of(alex, sam);
            customerRepository.saveAll(customers);
             */

            createRandomCustomer(customerRepository, passwordEncoder);
            //testBucketUploadAndDownload(s3Service, s3Buckets);
        };
    }

    private static void testBucketUploadAndDownload(S3Service s3Service,
                                                    S3Buckets s3Buckets) {
        s3Service.putObject(
                s3Buckets.getCustomer(),
                "test/hello",
                "Hello World".getBytes()
        );

        byte[] objectS3 = s3Service.getObject(
                s3Buckets.getCustomer(),
                "test/hello"
        );

        System.out.println("Hooray: " + new String(objectS3));
    }

    private static void createRandomCustomer(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        var faker = new Faker();
        Name name = faker.name();
        Random random = new Random();
        String firstName = name.firstName();
        String lastName = name.lastName();
        int age = random.nextInt(16, 99);
        Gender gender = age % 2 == 0 ? Gender.Male : Gender.Female;
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@skynet.com";

        Customer customer = new Customer(
                firstName +" "+ lastName,
                email,
                passwordEncoder.encode("password"),
                age,
                gender);

        customerRepository.save(customer);
        System.out.println(email);
    }
}

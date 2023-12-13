package com.skynet.customer;

import com.skynet.exception.DuplicateResourceException;
import com.skynet.exception.RequestValidationException;
import com.skynet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerDAO customerDao;
    private final CustomerDTOMapper customerDTOMapper;
    private final PasswordEncoder passwordEncoder;

    //public CustomerService(@Qualifier("jpa") CustomerDAO customerDao) {this.customerDao = customerDao; }
    public CustomerService(@Qualifier("jdbc") CustomerDAO customerDao, CustomerDTOMapper customerDTOMapper, PasswordEncoder passwordEncoder) {
        this.customerDao = customerDao;
        this.customerDTOMapper = customerDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerDao.selectAllCustomers()
                .stream()
                .map(customerDTOMapper)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomer(Integer id) {
        return customerDao.selectCustomerById(id)
                .map(customerDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id [%s] not found".formatted(id))
                );
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        String email = customerRegistrationRequest.email();
        if(customerDao.existsPersonWithEmail(email)){
            throw new DuplicateResourceException("Customer with same email already exists");
        }

        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                passwordEncoder.encode(customerRegistrationRequest.password()),
                customerRegistrationRequest.age(),
                customerRegistrationRequest.gender());

        customerDao.insertCustomer(customer);
    }

    public void deleteCustomerById(Integer customerId) {
        if(!customerDao.existsPersonWithId(customerId)){
            throw new ResourceNotFoundException("Customer with id [%s] not found".formatted(customerId));
        }

        customerDao.deleteCustomerById(customerId);
    }

    public void updateCustomer(Integer customerId, CustomerUpdateRequest customerUpdateRequest){
        Customer customer = customerDao.selectCustomerById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id [%s] not found".formatted(customerId))
                );

        boolean changes = false;

        if(customerUpdateRequest.name() !=null && !customerUpdateRequest.name().equals(customer.getName())){
            customer.setName(customerUpdateRequest.name());
            changes = true;
        }

        if(customerUpdateRequest.age() !=null && !customerUpdateRequest.age().equals(customer.getAge())){
            customer.setAge(customerUpdateRequest.age());
            changes = true;
        }

        if(customerUpdateRequest.email() !=null && !customerUpdateRequest.email().equals(customer.getEmail())){

            if(customerDao.existsPersonWithEmail(customerUpdateRequest.email())){
                throw new DuplicateResourceException("Customer with same email already exists");
            }
            customer.setEmail(customerUpdateRequest.email());
            changes = true;
        }

        if(!changes){
            throw new RequestValidationException("No data changes required");
        }

        customerDao.updateCustomer(customer);
    }
}

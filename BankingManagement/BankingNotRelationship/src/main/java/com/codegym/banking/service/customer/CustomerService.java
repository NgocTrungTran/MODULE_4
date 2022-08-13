package com.codegym.banking.service.customer;

import com.codegym.banking.model.Customer;
import com.codegym.banking.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

//    @Override
//    public void createCustomer(String full_name, String email, String phone, String address) {
//        customerRepository.createCustomer ( full_name, email, phone, address );
//    }
    //    @Override
//    public boolean createCustomer(Customer customer) {
//        String sql = "CALL create_customer(:full_name, :email, :phone , :address);";
//        Query query = entityManager.createNativeQuery(sql);
//        query.setParameter("full_name", customer.getFull_name ());
//        query.setParameter("email", customer.getEmail ());
//        query.setParameter("phone", customer.getPhone ());
//        query.setParameter("address", customer.getAddress ());
//        System.out.println (query.executeUpdate ());
//        return query.executeUpdate() == 0;
//    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(Long balance, Long id) {
        customerRepository.updateCustomer ( balance, id );
    }


    @Override
    public void remove(Long id) {
        customerRepository.deleteById (id);
    }

    //    @Override
//    public Page<Customer> findAll(Pageable pageable) {
//        return customerRepository.findAll(pageable);
//    }
//    @Override
//    public Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable) {
//        return customerRepository.findAllByFirstNameContaining(firstname, pageable);
//    }
}

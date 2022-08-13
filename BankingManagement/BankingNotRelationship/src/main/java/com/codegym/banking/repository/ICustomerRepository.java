package com.codegym.banking.repository;

import com.codegym.banking.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
//    Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable);
//    @Query(value = "CALL create_customer(?, ?, ?, ?, @mess);", nativeQuery = true)
//    void createCustomer(@Param("full_name") String full_name,
//                        @Param("email") String email,
//                        @Param("phone") String phone,
//                        @Param("address") String address);
    @Query(value = "UPDATE banking_management_not_relationship.customers SET balance = :balance WHERE id = :id;", nativeQuery = true)
    void updateCustomer(@Param("balance") Long balance, @Param("id") Long id);
}

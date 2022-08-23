package com.bank.ajaxbankingmavenlocationregion.repository;

import com.bank.ajaxbankingmavenlocationregion.model.Customer;
import com.bank.ajaxbankingmavenlocationregion.model.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByIdIsNot (Long id);
    Iterable<Customer> findAllByDeletedIsFalse();
    List<Customer> findAllByDeletedIsFalseAndIdIsNot(Long id);

    @Query("SELECT new com.bank.ajaxbankingmavenlocationregion.model.dto.CustomerDTO (" +
            "c.id, " +
            "c.fullName, " +
            "c.email, " +
            "c.phone, " +
            "c.balance, " +
            "c.locationRegion" +
            ") " +
            "FROM Customer AS c"
    )
    List<CustomerDTO> findAllCustomerDTO();

    @Modifying
    @Query("UPDATE Customer AS c " +
            "SET c.balance = c.balance + :balance " +
            "WHERE c.id = :customerId")
    void incrementBalance(@Param("customerId") Long customerId, @Param("balance") BigDecimal balance);
}

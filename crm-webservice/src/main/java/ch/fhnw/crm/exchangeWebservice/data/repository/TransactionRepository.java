package ch.fhnw.crm.exchangeWebservice.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ch.fhnw.crm.exchangeWebservice.data.domain.Transaction;
import ch.fhnw.crm.exchangeWebservice.data.domain.User;
import ch.fhnw.crm.exchangeWebservice.data.domain.Item;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>  {

    //find a transaction by its ID
    Optional<Transaction> findById(Long id);


    //find a transaction by transactionDate
    List<Transaction> findByTransactionDate(String transactionDate);

    //fin all transactions between two specific dates
    List<Transaction> findByTransactionDateBetween(String startDate, String endDate);


    //find all transactions belonging to a specific user
    @Query("SELECT t FROM Transaction t JOIN t.providinguser u WHERE u.userId = :userId")
    List<Transaction> findByUser_Id(Long userId);


    //Count the number of transactions
    long count();

    //Count the number of transactions involving a specific user
    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.providinguser = :providinguser OR t.receivinguser = :receivinguser")
    long countByProvidingUserOrReceivingUSer(User providinguser, User receivinguser);

}


    
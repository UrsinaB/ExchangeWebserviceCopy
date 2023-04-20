package ch.fhnw.crm.exchangeWebservice.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ch.fhnw.crm.exchangeWebservice.data.domain.Transaction;
import ch.fhnw.crm.exchangeWebservice.data.domain.User;
import ch.fhnw.crm.exchangeWebservice.data.domain.Item;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>  {

    //find a transaction by its ID
    Optional<Transaction> findById(Long id);

    //finda all transactions involving a specific user
    List<Transaction> findAllByProvidingUserOrReceivingUSer(User providingUser, User receivingUser);

    //find a transaction by a specific item title
    List<Transaction> findByItemTitle(String itemTitle);

    //find a transaction by transactionDate
    List<Transaction> findByTransactionDate(String transactionDate);

    //fin all transactions between two specific dates
    List<Transaction> findByTransactionDateBetween(String startDate, String endDate);


    //find all transactions belonging to a specific user
    List<Transaction> findByUser(User user);
    List<Transaction> findByUser_Id(Long userId);
    List<Transaction> findByUsername(String username);

    //Count the number of transactions
    long count();

    //Count the number of transactions involving a specific user
    long countByProvidingUserOrReceivingUSer(User providingUser, User receivingUser);

}


    
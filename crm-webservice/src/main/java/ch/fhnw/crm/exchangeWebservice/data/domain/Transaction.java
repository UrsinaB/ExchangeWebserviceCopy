package ch.fhnw.crm.exchangeWebservice.data.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    private LocalDate transactionDate;

    @OneToOne(mappedBy = "transaction")
    @JoinColumn(name = "itemId", nullable = false)
    private Item transactedItem;

    @OneToOne(mappedBy = "transaction")
    @JoinColumn(name ="UserId", nullable = false)
    private User providinguser;

    @OneToOne(mappedBy = "transaction")
    @JoinColumn(name ="UserId", nullable = false)
    private User receivinguser;

    public Transaction() {
    }

    

    
}

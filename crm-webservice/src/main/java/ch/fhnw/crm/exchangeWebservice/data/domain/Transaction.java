package ch.fhnw.crm.exchangeWebservice.data.domain;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "transactions")
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    @OneToOne(mappedBy = "transaction")
    @JoinColumn(name = "itemId", nullable = false)
    private Item transactedItem;

    // one Transaction has one providing user and one receiving user

    @ManyToOne
    @JoinColumn(name ="providinguserId", nullable = false)
    private User providinguser;

    @ManyToOne
    @JoinColumn(name ="receivinguserId", nullable = false)
    private User receivinguser;

    public Transaction() {
    }

    // getters and setters

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    

    
}

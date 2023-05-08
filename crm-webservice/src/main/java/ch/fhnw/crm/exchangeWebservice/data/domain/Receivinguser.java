
package ch.fhnw.crm.exchangeWebservice.data.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class  Receivinguser extends User {

    // one user can have many transactions as receiving user
	@OneToMany(mappedBy = "receivinguser")
	@JsonIgnore
	private List<Transaction> receivingTransactions;

        
        public Receivinguser(String username, String firstname, String lastname, String email, String password) {
            super();
        }

        public List<Transaction> getReceivingTransactions() {
            return receivingTransactions;
        }

        public void setReceivingTransactions(List<Transaction> receivingTransactions) {
            this.receivingTransactions = receivingTransactions;
        }
 
}

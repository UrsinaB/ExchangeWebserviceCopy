package ch.fhnw.crm.exchangeWebservice.data.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class  Providinguser extends User {



        // one user can have many transactions as providing user
	@OneToMany(mappedBy = "providinguser")
	@JsonIgnore
	private List<Transaction> providingTransactions;

	

    public Providinguser(String username, String firstname, String lastname, String email, String password) {
        super();
    }


    public List<Transaction> getProvidingTransactions() {
        return providingTransactions;
    }

    public void setProvidingTransactions(List<Transaction> providingTransactions) {
        this.providingTransactions = providingTransactions;
    }



}

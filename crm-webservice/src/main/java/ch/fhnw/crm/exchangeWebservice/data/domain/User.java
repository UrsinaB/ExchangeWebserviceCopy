package ch.fhnw.crm.exchangeWebservice.data.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Hidden;

@Entity
public class User {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Hidden
	private Long userId;

	@NotEmpty(message = "Please provide a username.")
	private String username;

	@JsonIgnore
	private String role = "USER";

	@Email(message = "Please provide a valid e-mail.")
	@NotEmpty(message = "Please provide an e-mail.")
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // only create object property from JSON
	private String password;

	@Transient // will not be stored in DB
	@Hidden
	private String remember;

	//one user can have many items

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Item> items;

	// one user can have many transactions as providing user
	@OneToMany(mappedBy = "providinguser")
	@JsonIgnore
	private List<Transaction> providingTransactions;

	// one user can have many transactions as receiving user
	@OneToMany(mappedBy = "receivinguser")
	@JsonIgnore
	private List<Transaction> receivingTransactions;
	
    public User() {
	}

	public User(String username, String email, String password, String remember) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.remember = remember;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setName(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getRemember() {
		return remember;
	}

	public void setRemember(String remember) {
		this.remember = remember;
	}

    public void setUsername(String username2) {
    }

    public String getRole() {
        return null;
    }

	

	
}

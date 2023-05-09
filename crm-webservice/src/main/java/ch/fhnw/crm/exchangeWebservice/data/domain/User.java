package ch.fhnw.crm.exchangeWebservice.data.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Hidden;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Hidden
	private Long userId;

	@NotEmpty(message = "Please provide a username.")
	private String username;

	@NotEmpty
	private String firstname;

	@NotEmpty
	private String lastname;

	@Email(message = "Please provide a valid e-mail.")
	@NotEmpty(message = "Please provide an e-mail.")
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // only create object property from JSON
	private String password;

	@Transient // will not be stored in DB
	@Hidden
	private String remember;

	// the date the user was registered
	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@GeneratedValue
	private Date userCreationDate;

	//one user can have many items
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Item> items;

	
    public User() {
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

	public void setUserame(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String name) {
		this.firstname = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String name) {
		this.lastname = name;
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

	public Date getUserCreationDate() {
		return userCreationDate;
	}

	public void setUserCreationDate(Date userCreationDate2) {
		this.userCreationDate = userCreationDate2;
	}
	
	
}

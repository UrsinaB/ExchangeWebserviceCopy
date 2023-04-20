package ch.fhnw.crm.exchangeWebservice.data.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "items")
public class Item {

    @Id
	@GeneratedValue
	@Hidden
	private Long id;

	@NotEmpty(message = "Please provide a title for your item")
	private String title;

	@NotEmpty(message ="Please provide a description for your item")
	private String description;

	@Column(name ="ListingDate")
	private LocalDate listingDate;

	@Enumerated(EnumType.STRING)
	private ItemStatus itemStatus;

	@OneToMany
	@JoinColumn(name = "categoryId")
	private ItemCategory itemCategory;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@OneToOne
	@JoinColumn(name = "transactionId")
	private Transaction transaction;

	//constructors, getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}

package com.zach.beltexam.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.*;

@Entity	
@Table(name = "shows")
public class Show {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Size(min = 3, max = 30, message = "Title must not be empty")
	private String title;
	
	@Size(min = 3, max = 30, message = "Network must not be empty")
	private String network;
	

	private double rating;
	
	

	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "show")
	private List<UserRating> whoRated;

	
	
	public Show() {
		
	}
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
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	

	public List<UserRating> getWhoRated() {
		return whoRated;
	}
	public void setWhoRated(List<UserRating> whoRated) {
		this.whoRated = whoRated;
	}
	
	
	
	
	
	
	
	
}

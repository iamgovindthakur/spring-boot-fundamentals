package com.iamgovindthakur.transactional.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Greeting {

	@Id
	@GeneratedValue
	private Long id;

	private String text;

	public Greeting() {

	}

	public Greeting(String string) {
		// TODO Auto-generated constructor stub
		this.text = string;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}

package com.application.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seckill {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private long userId;

	private String context;

	private String time;




	public Seckill() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}

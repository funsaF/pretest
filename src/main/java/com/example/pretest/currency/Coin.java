package com.example.pretest.currency;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Coin {
	@Id
	@SequenceGenerator(name = "coin_sequence", sequenceName = "coin_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coin_sequence")
	private Long id;
	private String currency; // 幣別
	private String cur_desc; // 中文

	public Coin() {
		super();
	}

	public Coin(Long id, String currency, String cur_desc) {
		super();
		this.id = id;
		this.currency = currency;
		this.cur_desc = cur_desc;
	}

	public Coin(String currency, String cur_desc) {
		super();
		this.currency = currency;
		this.cur_desc = cur_desc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCur_desc() {
		return cur_desc;
	}

	public void setCur_desc(String cur_desc) {
		this.cur_desc = cur_desc;
	}

	@Override
	public String toString() {
		return "Coin [Currency=" + currency + ", Cur_desc=" + cur_desc + "]";
	}

}

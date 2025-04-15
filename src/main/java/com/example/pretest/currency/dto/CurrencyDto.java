package com.example.pretest.currency.dto;

import jakarta.validation.constraints.DecimalMin;

public class CurrencyDto {
	private String cur;
	private String cur_desc;
	@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero.")
	private Double rate;

	public CurrencyDto(String cur, String cur_desc, Double rate) {
		this.cur = cur;
		this.cur_desc = cur_desc;
		this.rate = rate;
	}

	public String getCur() {
		return cur;
	}

	public void setCur(String cur) {
		this.cur = cur;
	}

	public String getCur_desc() {
		return cur_desc;
	}

	public void setCur_desc(String cur_desc) {
		this.cur_desc = cur_desc;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

}

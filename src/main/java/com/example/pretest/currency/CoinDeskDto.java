package com.example.pretest.currency;

import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class CoinDeskDto {
	@NotNull
	private LocalDateTime updated;
	@NotNull
	private String currency;
	private String currency_desc;
	@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero.")
	private Double rate;

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrency_desc() {
		return currency_desc;
	}

	public void setCurrency_desc(String currency_desc) {
		this.currency_desc = currency_desc;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
}

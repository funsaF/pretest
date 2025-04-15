package com.example.pretest.currency.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class CoinDeskNewDto {
	@NotNull
	private String updated;
	private List<CurrencyDto> currencyList;

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public List<CurrencyDto> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<CurrencyDto> currencyList) {
		this.currencyList = currencyList;
	}

}
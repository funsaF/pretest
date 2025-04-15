package com.example.pretest.currency.dto;

public class BpiDto {
	private CurInfoDto USD;
	private CurInfoDto GBP;
	private CurInfoDto EUR;

	public CurInfoDto getUSD() {
		return USD;
	}

	public void setUSD(CurInfoDto uSD) {
		USD = uSD;
	}

	public CurInfoDto getGBP() {
		return GBP;
	}

	public void setGBP(CurInfoDto gBP) {
		GBP = gBP;
	}

	public CurInfoDto getEUR() {
		return EUR;
	}

	public void setEUR(CurInfoDto eUR) {
		EUR = eUR;
	}

}

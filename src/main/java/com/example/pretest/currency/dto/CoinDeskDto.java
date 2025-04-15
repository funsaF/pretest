package com.example.pretest.currency.dto;

import java.util.Map;

public class CoinDeskDto {
	private TimeDto time;
	private String disclaimer;
	private String chartName;
	private Map<String, CurInfoDto> bpi; // 以貨幣碼為鍵，儲存各個貨幣的資料
	public TimeDto getTime() {
		return time;
	}

	public void setTime(TimeDto time) {
		this.time = time;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public Map<String, CurInfoDto> getBpi() {
		return bpi;
	}

	public void setBpi(Map<String, CurInfoDto> bpi) {
		this.bpi = bpi;
	}
}

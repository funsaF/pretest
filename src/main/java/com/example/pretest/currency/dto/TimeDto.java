package com.example.pretest.currency.dto;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class TimeDto {
	@JsonProperty("updated")
	@JsonDeserialize(using = CustomDateDeserializer.class) // 自定義反序列化
	private String updated;

	@JsonProperty("updatedISO")
	@JsonDeserialize(using = CustomDateDeserializer.class)
	private String updatedISO;

	@JsonProperty("updateduk")
	@JsonDeserialize(using = CustomDateDeserializer.class)
	private String updatedUK;

	// 自定義的日期反序列化器
	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getUpdatedISO() {
		return updatedISO;
	}

	public void setUpdatedISO(String updatedISO) {
		this.updatedISO = updatedISO;
	}

	public String getUpdatedUK() {
		return updatedUK;
	}

	public void setUpdatedUK(String updatedUK) {
		this.updatedUK = updatedUK;
	}

	public static class CustomDateDeserializer extends com.fasterxml.jackson.databind.JsonDeserializer<String> {

		@Override
		public String deserialize(com.fasterxml.jackson.core.JsonParser p,
				com.fasterxml.jackson.databind.DeserializationContext ctxt) throws java.io.IOException {
			String dateString = p.getText();

			// 定義不同的日期格式
			DateTimeFormatter updatedFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm:ss z", Locale.ENGLISH);
			DateTimeFormatter updatedISOFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
			DateTimeFormatter updatedUKFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy 'at' HH:mm z",
					Locale.ENGLISH);

			try {
				// 嘗試解析並格式化日期
				ZonedDateTime zonedDateTime;
				if (dateString.contains("UTC")) {
					zonedDateTime = ZonedDateTime.parse(dateString, updatedFormatter);
				} else if (dateString.contains("BST")) {
					zonedDateTime = ZonedDateTime.parse(dateString, updatedUKFormatter);
				} else {
					zonedDateTime = ZonedDateTime.parse(dateString, updatedISOFormatter);
				}

				// 返回格式化後的日期
				return zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
			} catch (Exception e) {
				throw new IOException("無法解析日期格式", e);
			}
		}
	}
}

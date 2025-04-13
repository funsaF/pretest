package com.example.pretest.currency;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoinConfig {
	@Bean
	CommandLineRunner commandLineRunner(CoinRepository repository) {
		return args -> {
			Coin twd = new Coin("TWD", "台幣");
			Coin usd = new Coin("USD", "美元");
			Coin jpy = new Coin("JPY", "日圓");
			repository.saveAll(List.of(twd, usd, jpy));
		};
	}

}

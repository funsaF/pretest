package com.example.pretest.currency;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import reactor.core.publisher.Mono;

@Service
public class CoinService {
	private final CoinRepository coinRepository;

	@Autowired
	public CoinService(CoinRepository coinRepository) {
		this.coinRepository = coinRepository;
	}

	@Autowired
	private WebClient.Builder webClientBuilder;

	public List<Coin> getCoins() {
		return coinRepository.findAll();
	}

	public void addNewCoin(Coin coin) {
		Optional<Coin> coinOptional = coinRepository.findByCurrency(coin.getCurrency());
		if (coinOptional.isPresent()) {
			throw new IllegalStateException("幣別已存在");
		}
		coinRepository.save(coin);
	}

	public void deleteCoin(Long id) {
		if (!coinRepository.existsById(id)) {
			throw new IllegalStateException("coinId = " + id + " doesn't exist.");
		}
		coinRepository.deleteById(id);
	}

	@Transactional
	public void updateCoin(Long id, String currency, String desc) {
		Coin coin = coinRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("coinId " + id + " doesn't exist."));
		if (currency != null && currency.length() > 0 && !Objects.equals(coin.getCurrency(), currency)) {
			coin.setCurrency(currency);
		}
		if (desc != null && desc.length() > 0 && !Objects.equals(coin.getCur_desc(), desc)) {
			coin.setCur_desc(desc);
		}
	}

	public Mono<String> getApiCoindesk() {
		// 要呼叫外部 API
		String apiUrl = "https://kengp3.github.io/blog/coindesk.json";
		return webClientBuilder.build().get().uri(apiUrl).retrieve().bodyToMono(String.class);
	}

	public CoinDeskDto getCoinDeskNew() {
		Mono<String> coindesk = this.getApiCoindesk();
		CoinDeskDto coindeskinfo = new CoinDeskDto();
		Mono<Object> newtime = coindesk.map(jsonString->{
//			try {
//				JsonNode rootNode = ObjectMapper.readTree(jsonString);
//				JsonNode currencyNode = rootNode.path(currency);
//				return currencyNode.path("time").aslocalDatetime();
//			} catch (Exception e) {
//				throw new RuntimeException("Error parsing JSON", e);
//			}
		});
	}
}

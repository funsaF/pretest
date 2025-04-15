package com.example.pretest.currency;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.pretest.currency.dto.CoinDeskDto;
import com.example.pretest.currency.dto.CoinDeskNewDto;
import com.example.pretest.currency.dto.CurrencyDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class CoinService {
	private final CoinRepository coinRepository;

	@Autowired
	public CoinService(CoinRepository coinRepository) {
		this.coinRepository = coinRepository;
	}

	@Autowired
	private WebClient.Builder webClientBuilder;
	@Autowired
	private ObjectMapper objectMapper;

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

//	public CoinDeskNewDto getCoinDeskNew() {
	public Mono<CoinDeskNewDto> getCoinDeskNew() {
		Mono<String> coindesk = this.getApiCoindesk();
		//解析json
		Mono<CoinDeskDto> coinDeskDto = coindesk
				.flatMap(json -> Mono.fromCallable(() -> objectMapper.readValue(json, CoinDeskDto.class))
						.subscribeOn(Schedulers.boundedElastic()));
		// 查出所有幣別中文
	    Mono<Map<String, String>> coinMapMono = Mono.fromCallable(() -> coinRepository.findAll().stream()
	            .collect(Collectors.toMap(Coin::getCurrency, Coin::getCur_desc)))
	            .subscribeOn(Schedulers.boundedElastic());
		// 處理新的dto
		return coinDeskDto.zipWith(coinMapMono, (original, coinMap) -> {
			CoinDeskNewDto newDto = new CoinDeskNewDto();
			newDto.setUpdated(original.getTime().getUpdated());
			newDto.setCurrencyList(original.getBpi().entrySet().stream().map(entry -> {
				String cur = entry.getKey();
				String curDesc = coinMap.getOrDefault(cur,"未知幣別");
				Double rate = Double.parseDouble(entry.getValue().getRate().replace(",", ""));
				return new CurrencyDto(cur,curDesc, rate);
			}).collect(Collectors.toList()));
			return newDto;
		});
	}
}

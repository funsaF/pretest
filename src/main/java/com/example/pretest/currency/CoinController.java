package com.example.pretest.currency;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "api/coin")
public class CoinController {
	private final CoinService coinService;

	@Autowired
	public CoinController(CoinService coinService) {
		this.coinService = coinService;
	}

	@GetMapping
	public List<Coin> getCoins() {
		return coinService.getCoins();
	}

	@PostMapping
	public void addNewCoin(@RequestBody Coin coin) {
		coinService.addNewCoin(coin);
	}

	@DeleteMapping(path = "{coinId}")
	public void deleteCoin(@PathVariable("coinId") Long id) {
		coinService.deleteCoin(id);
	}

	@PutMapping(path = "{coinId}")
	public void updateCoin(@PathVariable("coinId") Long id, @RequestParam(required = false) String currency,
			@RequestParam(required = false) String desc) {
		coinService.updateCoin(id, currency, desc);
	}

	@GetMapping("/getCoindesk")
	public Mono<String> getCoindesk() {
		return coinService.getApiCoindesk();
	}
	@GetMapping("/getCoindesk2")
	public CoinDeskDto getCoindeskNew() {
		return coinService.getCoinDeskNew();
	}
}

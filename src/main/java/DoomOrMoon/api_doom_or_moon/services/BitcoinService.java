package DoomOrMoon.api_doom_or_moon.services;

import DoomOrMoon.api_doom_or_moon.exceptions.ApiConnectionException;
import DoomOrMoon.api_doom_or_moon.exceptions.InsufficientDataException;
import DoomOrMoon.api_doom_or_moon.models.Bitcoin;
import DoomOrMoon.api_doom_or_moon.repositories.BitcoinRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BitcoinService {
    private final BitcoinRepository bitcoinRepository;
    private final RestTemplate restTemplate;

    @Value("${coingecko.api.key}")
    private String apiKey;

    public BitcoinService(BitcoinRepository bitcoinRepository, RestTemplate restTemplate) {
        this.bitcoinRepository = bitcoinRepository;
        this.restTemplate = restTemplate;
    }

    public List<Bitcoin> buscarESalvarPrecoBitcoin() {

        Optional<Bitcoin> primeiroBitcoin = bitcoinRepository.findById(1L);
        if (primeiroBitcoin.isPresent() && primeiroBitcoin.get().getLastRequest().toLocalDate().equals(LocalDate.now())) {
            return bitcoinRepository.findAllBitcoins();
        }

        String url = "https://api.coingecko.com/api/v3/coins/bitcoin/market_chart?vs_currency=usd&days=200";

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-cg-api-key", apiKey);
        headers.set("Accept", "application/json");

        ResponseEntity<Map> response = restTemplate.exchange(
                url, HttpMethod.GET, new HttpEntity<>(headers), Map.class);

        if (response.getBody() == null || !response.getBody().containsKey("prices")) {
            throw new ApiConnectionException("Resposta inválida da API CoinGecko");
        }

        List<List<Double>> prices = (List<List<Double>>) response.getBody().get("prices");
        List<Bitcoin> bitcoinList = new ArrayList<>();

        for (List<Double> entry : prices) {
            Double preco = entry.get(1); // Apenas o valor do Bitcoin (posição 1)
            Bitcoin bitcoin = new Bitcoin(preco);
            bitcoinList.add(bitcoin);
        }

        bitcoinRepository.deleteAllInBatch();
        bitcoinRepository.resetAutoIncrement();
        bitcoinRepository.saveAll(bitcoinList);
        return bitcoinList;
    }

}


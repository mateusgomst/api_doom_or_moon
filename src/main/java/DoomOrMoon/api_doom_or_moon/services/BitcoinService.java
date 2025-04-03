package DoomOrMoon.api_doom_or_moon.services;

import DoomOrMoon.api_doom_or_moon.models.Bitcoin;
import DoomOrMoon.api_doom_or_moon.repositories.BitcoinRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public void buscarESalvarPrecoBitcoin() {

        Optional<Bitcoin> ultimoRegistro = bitcoinRepository.findUltimoRegistro();
        if (ultimoRegistro.isPresent() && ultimoRegistro.get().getCreatedAt().toLocalDate().equals(LocalDate.now())) {
            throw new IllegalStateException("Preço do Bitcoin já registrado hoje.");
        }

        String url = "https://api.coingecko.com/api/v3/coins/bitcoin/market_chart?vs_currency=usd&days=200";

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-cg-api-key", apiKey);
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        if (response.getBody() == null || !response.getBody().containsKey("prices")) {
            throw new IllegalArgumentException("Resposta inválida da API CoinGecko");
        }

        List<List<Double>> prices = (List<List<Double>>) response.getBody().get("prices");
        for (List<Double> entry : prices) {
            Double price = entry.get(1);
            Bitcoin bitcoin = new Bitcoin(price);
            bitcoinRepository.save(bitcoin);
        }
    }

}


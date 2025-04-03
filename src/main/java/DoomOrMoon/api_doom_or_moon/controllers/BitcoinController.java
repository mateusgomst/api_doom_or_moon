package DoomOrMoon.api_doom_or_moon.controllers;

import DoomOrMoon.api_doom_or_moon.services.BitcoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bitcoin")
public class BitcoinController {

    private final BitcoinService bitcoinService;

    public BitcoinController(BitcoinService bitcoinService) {
        this.bitcoinService = bitcoinService;
    }

    @GetMapping("/doomormoon")
    public ResponseEntity<String> fetchAndSavePrices() {
        try {
            bitcoinService.buscarESalvarPrecoBitcoin();
            return ResponseEntity.ok("Preços do Bitcoin armazenados com sucesso!");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body("Preço do Bitcoin já registrado hoje.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao buscar preços: " + e.getMessage());
        }
    }
}

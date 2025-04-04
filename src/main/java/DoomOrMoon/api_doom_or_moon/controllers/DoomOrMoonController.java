package DoomOrMoon.api_doom_or_moon.controllers;

import DoomOrMoon.api_doom_or_moon.models.Bitcoin;
import DoomOrMoon.api_doom_or_moon.services.BitcoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping()
public class DoomOrMoonController {

    private final BitcoinService bitcoinService;

    public DoomOrMoonController(BitcoinService bitcoinService) {
        this.bitcoinService = bitcoinService;
    }

    @GetMapping("/bitcoin")
    public ResponseEntity<?> fetchAndSavePrices() {
        try {
            List<Bitcoin> bitcoinList = bitcoinService.buscarESalvarPrecoBitcoin();
            return ResponseEntity.ok(bitcoinList);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body("Preço do Bitcoin já registrado hoje.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao buscar preços: " + e.getMessage());
        }
    }
}

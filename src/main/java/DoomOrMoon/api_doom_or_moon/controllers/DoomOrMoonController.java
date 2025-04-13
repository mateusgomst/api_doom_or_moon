package DoomOrMoon.api_doom_or_moon.controllers;

import DoomOrMoon.api_doom_or_moon.models.Bitcoin;
import DoomOrMoon.api_doom_or_moon.utils.BitcoinAnalysisResult;
import DoomOrMoon.api_doom_or_moon.services.BitcoinService;
import DoomOrMoon.api_doom_or_moon.utils.BitcoinAnalysis;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bitcoin")
public class DoomOrMoonController {

    private final BitcoinService bitcoinService;
    private final BitcoinAnalysis bitcoinAnalysis;

    public DoomOrMoonController(BitcoinService bitcoinService, BitcoinAnalysis bitcoinAnalysis) {
        this.bitcoinService = bitcoinService;
        this.bitcoinAnalysis = bitcoinAnalysis;
    }

   @GetMapping("/historico")
    public ResponseEntity<List<Bitcoin>> historicoValores() {
       List<Bitcoin> bitcoinList = bitcoinService.buscarESalvarPrecoBitcoin();
       return ResponseEntity.ok(bitcoinList);
    }

    @GetMapping("/curto-prazo")
    public ResponseEntity<BitcoinAnalysisResult> analiseCurtoPrazo() {
        List<Bitcoin> dados = bitcoinService.buscarESalvarPrecoBitcoin();
        BitcoinAnalysisResult resultado = bitcoinAnalysis.analisarTendenciaCurtoPrazo(dados);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/longo-prazo")
    public ResponseEntity<BitcoinAnalysisResult> analiseLongoPrazo() {
        List<Bitcoin> dados = bitcoinService.buscarESalvarPrecoBitcoin();
        BitcoinAnalysisResult resultado = bitcoinAnalysis.analisarTendenciaLongoPrazo(dados);
        return ResponseEntity.ok(resultado);
    }
}
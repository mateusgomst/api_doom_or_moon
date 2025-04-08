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

    @GetMapping("/curto-prazo")
    public ResponseEntity<BitcoinAnalysisResult> analiseCurtoPrazo() {
        try {
            List<Bitcoin> dados = bitcoinService.buscarESalvarPrecoBitcoin();
            return ResponseEntity.ok(bitcoinAnalysis.analisarTendenciaCurtoPrazo(dados));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new BitcoinAnalysisResult(
                            BitcoinAnalysisResult.Tendencia.NEUTRA,
                            "Erro na análise: " + e.getMessage(),
                            null
                    ));
        }
    }

    @GetMapping("/longo-prazo")
    public ResponseEntity<BitcoinAnalysisResult> analiseLongoPrazo() {
        try {
            List<Bitcoin> dados = bitcoinService.buscarESalvarPrecoBitcoin();
            return ResponseEntity.ok(bitcoinAnalysis.analisarTendenciaLongoPrazo(dados));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new BitcoinAnalysisResult(
                            BitcoinAnalysisResult.Tendencia.NEUTRA,
                            "Erro na análise: " + e.getMessage(),
                            null
                    ));
        }
    }

}
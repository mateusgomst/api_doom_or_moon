package DoomOrMoon.api_doom_or_moon.utils;

public class BitcoinAnalysisResult {
    private final Tendencia tendencia;
    private final String motivo;
    private final Double forca;  // Pode ser usado para medir a força do sinal

    public enum Tendencia {
        ALTA, BAIXA, NEUTRA
    }

    public BitcoinAnalysisResult(Tendencia tendencia, String motivo, Double forca) {
        this.tendencia = tendencia;
        this.motivo = motivo;
        this.forca = forca;
    }

    // Getters
    public Tendencia getTendencia() { return tendencia; }
    public String getMotivo() { return motivo; }
    public Double getForca() { return forca; }
}
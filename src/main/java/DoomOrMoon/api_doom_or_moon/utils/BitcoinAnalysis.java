package DoomOrMoon.api_doom_or_moon.utils;

import DoomOrMoon.api_doom_or_moon.models.Bitcoin;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BitcoinAnalysis {


    /**
     * SMA(Simple Moving Average)
    O que é: Média dos preços de fechamento dos últimos n dias.
        Para que serve:
        SMA 20 dias: Identifica tendências de curto prazo.
        SMA 200 dias: Define tendências de longo prazo (suporte/resistência).
     */

    public Double calcularSMA(List<Bitcoin> bitcoinList, int dias) {
        if (bitcoinList.size() < dias) return null; // Evita erros se a lista for menor que "dias"

        double soma = 0;
        for (int i = bitcoinList.size() - dias; i < bitcoinList.size(); i++) {
            soma += bitcoinList.get(i).getPrice();
        }
        return soma / dias;
    }


    /**
     * EMA(Exponential Moving Average)
        O que é: Média móvel exponencial (EMA) é uma média móvel que dá mais peso aos preços mais recentes.
        Para que serve?
        Identificar tendências:
        EMA acima do preço → Tendência de baixa.
        EMA abaixo do preço → Tendência de alta.

        Preço Bitcoin:   | 53,000 |
        EMA 3 dias:      | 51,875 | → Tendência de alta (preço > EMA).

        uma das melhores formas de usar é EMA 12 > EMA 26 E RSI > 50 (confirma momentum).

        Para day trading:
            EMA 9/21 (mais sensível).
        Para investimento:
            EMA 50/200 (mais lento, mas robusto).

     */

    public Double calcularEMA(List<Bitcoin> bitcoinList, int dias) {
        if (bitcoinList.size() < dias) return null;

        double multiplicador = 2.0 / (dias + 1);
        double ema = calcularSMA(bitcoinList.subList(0, dias), dias); // SMA inicial

        for (int i = dias; i < bitcoinList.size(); i++) {
            double precoAtual = bitcoinList.get(i).getPrice();
            ema = (precoAtual * multiplicador) + (ema * (1 - multiplicador));
        }
        return ema;
    }


    /**
     * RSI(Relative Strength Index)
    RSI (Relative Strength Index)

    O que é: Indicador de momentum que mede a velocidade e variação dos movimentos de preço, oscilando entre 0 e 100.

    Para que serve?
    Identificar condições de sobrecompra (RSI > 70) e sobrevenda (RSI < 30).
    Confirmar momentum (RSI > 50 = tendência de alta; RSI < 50 = tendência de baixa).
    Detectar divergências (ex: preço sobe, RSI cai → possível reversão).
    Exemplo Prático:

    Copy
    Preço Bitcoin:   | 53,000 |
    RSI 14 dias:     | 75     | → Sobrecompra (possível correção).
    Melhores formas de usar:

    Day Trading: RSI 14 + Bollinger Bands (saída das bandas + RSI extremo).
    Swing Trading: RSI 21 + EMA 50 (RSI > 50 e preço acima da EMA 50 confirma tendência).
    Divergências:
    De alta: Preço faz fundo mais baixo, RSI faz fundo mais alto.
    De baixa: Preço faz topo mais alto, RSI faz topo mais baixo.
    Configurações comuns:

    Curto Prazo: RSI 7-14 (mais sensível).
    Longo Prazo: RSI 21-30 (menos sinais falsos).
    Limitações:

    Em mercados muito voláteis, o RSI pode ficar "grudado" em sobrecompra/sobrevenda.
    Combinar com volume ou MACD para filtrar sinais.
     */

    public Double calcularRSI(List<Bitcoin> bitcoinList, int periodo) {
        if (bitcoinList.size() <= periodo) return null;

        List<Double> ganhos = new ArrayList<>();
        List<Double> perdas = new ArrayList<>();

        for (int i = 1; i < bitcoinList.size(); i++) {
            double variacao = bitcoinList.get(i).getPrice() - bitcoinList.get(i - 1).getPrice();
            if (variacao > 0) {
                ganhos.add(variacao);
                perdas.add(0.0);
            } else {
                perdas.add(Math.abs(variacao));
                ganhos.add(0.0);
            }
        }

        // Média dos primeiros "periodo" dias para AG e AP iniciais
        double ag = ganhos.subList(0, periodo).stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double ap = perdas.subList(0, periodo).stream().mapToDouble(Double::doubleValue).average().orElse(0);

        // Cálculo do RSI suavizado (EMA dos ganhos e perdas)
        for (int i = periodo; i < bitcoinList.size() - 1; i++) {
            ag = (ag * (periodo - 1) + ganhos.get(i)) / periodo;
            ap = (ap * (periodo - 1) + perdas.get(i)) / periodo;
        }

        if (ap == 0) return 100.0; // Evita divisão por zero
        double rs = ag / ap;
        return 100 - (100 / (1 + rs));
    }

    /*
     * MACD(Moving Average Convergence Divergence)
    Exemplo:
    Preço Bitcoin:   | 53,000 |
    MACD Line:       | 500    |
    Signal Line:     | 300    | → Tendência de alta (MACD > Signal).
    */
    public Map<String, Double> calcularMACD(List<Bitcoin> bitcoinList) {
        if (bitcoinList.size() < 26) return null;

        double ema12 = calcularEMA(bitcoinList, 12);
        double ema26 = calcularEMA(bitcoinList, 26);
        double macdLine = ema12 - ema26;

        // Simulação da Signal Line (EMA 9 do MACD histórico - simplificado)
        double signalLine = calcularEMA(bitcoinList.subList(bitcoinList.size() - 9, bitcoinList.size()), 9);

        return Map.of(
                "macdLine", macdLine,
                "signalLine", signalLine,
                "histograma", macdLine - signalLine
        );
    }


    public BitcoinAnalysisResult analisarTendenciaCurtoPrazo(List<Bitcoin> bitcoinList) {
        Double ema9 = calcularEMA(bitcoinList, 9);
        Double ema21 = calcularEMA(bitcoinList, 21);
        Double rsi = calcularRSI(bitcoinList, 14);

        if (ema9 == null || ema21 == null || rsi == null) {
            return new BitcoinAnalysisResult(
                    BitcoinAnalysisResult.Tendencia.NEUTRA,
                    "Dados insuficientes",
                    null
            );
        }

        BitcoinAnalysisResult.Tendencia tendencia;
        String motivo;

        if (ema9 > ema21 && rsi > 50) {
            tendencia = BitcoinAnalysisResult.Tendencia.ALTA;
            motivo = String.format("EMA9 (%.2f) > EMA21 (%.2f) e RSI (%.2f) > 50", ema9, ema21, rsi);
        } else if (ema9 < ema21 && rsi < 50) {
            tendencia = BitcoinAnalysisResult.Tendencia.BAIXA;
            motivo = String.format("EMA9 (%.2f) < EMA21 (%.2f) e RSI (%.2f) < 50", ema9, ema21, rsi);
        } else {
            tendencia = BitcoinAnalysisResult.Tendencia.NEUTRA;
            motivo = "Sem sinal claro de tendência";
        }

        return new BitcoinAnalysisResult(tendencia, motivo, rsi);
    }

    public BitcoinAnalysisResult analisarTendenciaLongoPrazo(List<Bitcoin> bitcoinList) {
        Double ema50 = calcularEMA(bitcoinList, 50);
        Double ema200 = calcularEMA(bitcoinList, 200);

        if (ema50 == null || ema200 == null) {
            return new BitcoinAnalysisResult(
                    BitcoinAnalysisResult.Tendencia.NEUTRA,
                    "Dados insuficientes",
                    null
            );
        }

        double precoAtual = bitcoinList.get(bitcoinList.size() - 1).getPrice();
        BitcoinAnalysisResult.Tendencia tendencia;
        String motivo;

        if (ema50 > ema200 && precoAtual > ema200) {
            tendencia = BitcoinAnalysisResult.Tendencia.ALTA;
            motivo = String.format("EMA50 (%.2f) > EMA200 (%.2f) e Preço acima da EMA200", ema50, ema200);
        } else if (ema50 < ema200 && precoAtual < ema200) {
            tendencia = BitcoinAnalysisResult.Tendencia.BAIXA;
            motivo = String.format("EMA50 (%.2f) < EMA200 (%.2f) e Preço abaixo da EMA200", ema50, ema200);
        } else {
            tendencia = BitcoinAnalysisResult.Tendencia.NEUTRA;
            motivo = "Sem tendência dominante";
        }

        return new BitcoinAnalysisResult(tendencia, motivo, null);
    }

    public BitcoinAnalysisResult analisarTendenciaComMACD(List<Bitcoin> bitcoinList) {
        if (bitcoinList.size() < 26) {
            return new BitcoinAnalysisResult(
                    BitcoinAnalysisResult.Tendencia.NEUTRA,
                    "Dados insuficientes (mínimo 26 dias)",
                    null
            );
        }

        double ema12 = calcularEMA(bitcoinList, 12);
        double ema26 = calcularEMA(bitcoinList, 26);
        double macdLine = ema12 - ema26;
        double signalLine = calcularEMA(bitcoinList.subList(bitcoinList.size() - 9, bitcoinList.size()), 9);

        BitcoinAnalysisResult.Tendencia tendencia;
        String motivo;
        Double forca = Math.abs(macdLine - signalLine);

        if (macdLine > signalLine) {
            tendencia = BitcoinAnalysisResult.Tendencia.ALTA;
            motivo = String.format("Tendência de alta: MACD (%.2f) > Signal (%.2f)", macdLine, signalLine);
        } else if (macdLine < signalLine) {
            tendencia = BitcoinAnalysisResult.Tendencia.BAIXA;
            motivo = String.format("Tendência de baixa: MACD (%.2f) < Signal (%.2f)", macdLine, signalLine);
        } else {
            tendencia = BitcoinAnalysisResult.Tendencia.NEUTRA;
            motivo = "MACD igual à Signal Line - mercado indeciso";
        }

        return new BitcoinAnalysisResult(tendencia, motivo, forca);
    }
}



package DoomOrMoon.api_doom_or_moon.services;

import DoomOrMoon.api_doom_or_moon.repositories.BitcoinRepository;
import org.springframework.stereotype.Service;

@Service
public class CalculosBitcoinService {
    private final BitcoinRepository bitcoinRepository;

    public CalculosBitcoinService(BitcoinRepository bitcoinRepository){
        this.bitcoinRepository=bitcoinRepository;
    }

    /*
     public Double calcularMA(){
        double total = 0;

        for(Double valor:valores){
            total+=valor;
        }
        return total/valores.size();
    }
     */
}

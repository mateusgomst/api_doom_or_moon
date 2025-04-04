package DoomOrMoon.api_doom_or_moon.services;

import DoomOrMoon.api_doom_or_moon.models.Bitcoin;
import DoomOrMoon.api_doom_or_moon.repositories.BitcoinRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculosBitcoinService {

     public Double calcularMA(List<Bitcoin> bitcoinList){
        double total = 0;
        for(Bitcoin bitcoin:bitcoinList){
            total+=bitcoin.getPrice();
        }
        return total/bitcoinList.size();
    }

}

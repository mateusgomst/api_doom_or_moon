package DoomOrMoon.api_doom_or_moon.exceptions;

// Para erros de processamento de dados
public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message) {
        super(message);
    }
}
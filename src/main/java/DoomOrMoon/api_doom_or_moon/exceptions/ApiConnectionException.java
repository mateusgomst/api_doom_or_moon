package DoomOrMoon.api_doom_or_moon.exceptions;

// Para erros de conexão ou comunicação com APIs externas
public class ApiConnectionException extends RuntimeException {
    public ApiConnectionException(String message) {
        super(message);
    }
}
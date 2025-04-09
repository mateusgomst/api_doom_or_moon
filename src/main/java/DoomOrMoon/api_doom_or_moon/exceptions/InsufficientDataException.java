package DoomOrMoon.api_doom_or_moon.exceptions;

// Para dados inválidos ou insuficientes (já existe)
public class InsufficientDataException extends RuntimeException {
    public InsufficientDataException(String message) {
        super(message);
    }
}
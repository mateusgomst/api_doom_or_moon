package DoomOrMoon.api_doom_or_moon.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bitcoin")
public class Bitcoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime lastRequest;
    private Double price;

    public Bitcoin() {}

    public Bitcoin(Double price) {
        this.price = price;
        this.lastRequest = LocalDateTime.now();
    }

    public LocalDateTime getLastRequest() {
        return lastRequest;
    }

    public void setLastRequest(LocalDateTime createdAt) {
        this.lastRequest = createdAt;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

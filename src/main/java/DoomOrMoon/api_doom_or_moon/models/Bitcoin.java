package DoomOrMoon.api_doom_or_moon.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bitcoin")
public class Bitcoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime createdAt;
    private Double price;

    public Bitcoin() {}

    public Bitcoin(Double price) {
        this.price = price;
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

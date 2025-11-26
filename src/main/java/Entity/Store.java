package Entity;
import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="Store")
public class Store { // 가게 별도
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long store_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="map_id")
    private Map map;

    private String name;
    private String address;
    private String description;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}

package first.Entity;
import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="Store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int store_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="map_id")
    private Map map;

    private String name;
    private String address;
    private String description;
}

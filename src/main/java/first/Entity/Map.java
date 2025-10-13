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
@Table(name="Map")
public class Map { // 상위로 지도 지정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int map_id;

    private String location;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="member")
    private Member member;
}

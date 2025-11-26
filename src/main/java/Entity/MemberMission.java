package Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="MemberMission")
public class MemberMission { //멤버 미션 매핑
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable=false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mission_id", nullable=false)
    private Mission mission;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}

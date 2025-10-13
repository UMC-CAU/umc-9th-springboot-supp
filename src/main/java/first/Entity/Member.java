package first.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="users")
public class Member { //멤버관리
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int address;

    @Enumerated(EnumType.STRING)
    private State state;
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMission =new ArrayList<>();

    @OneToMany(mappedBy = "Member", cascade = CascadeType.ALL)
    private List<Member> member =new ArrayList<>();

    @OneToMany(mappedBy = "Member", cascade = CascadeType.ALL)
    private List<Member> memAlarm =new ArrayList<>();
}

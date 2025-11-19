package org.example.Entity;
import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="Alarm")
public class Alarm { // 알림설정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int alarm_id;

    private String title;
    private String description;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="member")
    private Member member;
}


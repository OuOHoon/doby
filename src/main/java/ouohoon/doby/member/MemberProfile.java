package ouohoon.doby.member;

import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
public class MemberProfile {

    @Id @GeneratedValue
    @Column(name = "member_profile_id")
    private Long id;

    @Column(length = 20, unique = true)
    private String nickname;
    private String imageUrl;
    private short level;
    private int exp;
    private int money;

    @UpdateTimestamp
    private LocalDateTime updatedTime;
}

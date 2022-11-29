package ouohoon.doby.member.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFormLogin {

    @Id @GeneratedValue
    @Column(name = "member_form_login_id")
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;

    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @OneToOne(mappedBy = "formLogin")
    private Member member;

    @Builder
    public MemberFormLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

package ouohoon.doby.member.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSocialLogin {

    @Id @GeneratedValue
    @Column(name = "member_social_login_id")
    private Long id;

    @Column(length = 50)
    private String provider;
    private String providerId;

    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @Builder
    public MemberSocialLogin(String provider, String providerId) {
        this.provider = provider;
        this.providerId = providerId;
    }
}

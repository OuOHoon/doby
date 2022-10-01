package ouohoon.doby.member;

import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class MemberFormLogin {

    @Id @GeneratedValue
    @Column(name = "member_form_login_id")
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @UpdateTimestamp
    private LocalDateTime updatedTime;
}

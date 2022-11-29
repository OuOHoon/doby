package ouohoon.doby.security.session;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class MySession {


    @Id @GeneratedValue
    @Column(name = "my_session_id")
    private Long id;
    private String sessionId;

    private LocalDateTime validTime;
    @CreationTimestamp
    private LocalDateTime createdTime;
}

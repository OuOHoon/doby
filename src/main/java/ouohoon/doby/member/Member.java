package ouohoon.doby.member;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ouohoon.doby.post.Post;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String nickname;
    private int level;
    private int exp;
    private int money;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();
}

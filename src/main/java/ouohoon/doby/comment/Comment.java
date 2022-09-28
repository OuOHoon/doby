package ouohoon.doby.comment;

import lombok.Getter;
import ouohoon.doby.member.Member;
import ouohoon.doby.post.Post;

import javax.persistence.*;

@Entity
@Getter
public class Comment {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Member author;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}

package ouohoon.doby.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ouohoon.doby.member.Member;
import ouohoon.doby.post.Post;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member author;
    @Column(length = 200)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Comment(String content) {
        this.content = content;
    }

    public void updateComment(String content) {
        this.content = content;
    }

    public void changePost(Post post) {
        post.getComments().add(this);
        this.post = post;
    }
    public void changeAuthor(Member author) {
        author.getComments().add(this);
        this.author = author;
    }
}

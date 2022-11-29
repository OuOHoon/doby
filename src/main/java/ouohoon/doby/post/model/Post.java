package ouohoon.doby.post.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ouohoon.doby.comment.Comment;
import ouohoon.doby.member.model.Member;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(length = 100)
    private String title;
    @Column(length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member author;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }
    public void changeAuthor(Member author) {
        author.getPosts().add(this);
        this.author = author;
    }
}

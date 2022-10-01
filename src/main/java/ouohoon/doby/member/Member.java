package ouohoon.doby.member;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ouohoon.doby.comment.Comment;
import ouohoon.doby.post.Post;
import ouohoon.doby.security.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "member_profile_id")
    private MemberProfile profile;

    @OneToOne
    @JoinColumn(name = "member_form_login_id")
    private MemberFormLogin formLogin;

    @OneToOne
    @JoinColumn(name = "member_social_login_id")
    private MemberSocialLogin socialLogin;

    @OneToMany(mappedBy = "author")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Comment> comments = new ArrayList<>();
}

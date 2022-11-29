package ouohoon.doby.post;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ouohoon.doby.member.MemberRepository;
import ouohoon.doby.member.model.Member;
import ouohoon.doby.post.model.Post;

import java.util.Optional;

@Service
public class PostService {

    private MemberRepository memberRepository;
    private PostRepository postRepository;

    public PostService(MemberRepository memberRepository, PostRepository postRepository) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    public PostDTO save(PostDTO postDTO) {
        SecurityContext context = SecurityContextHolder.getContext();
        String memberId = context.getAuthentication().getName();

        Post post = Post.builder()
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .build();
        Optional<Member> optionalMember = memberRepository.findById(Long.valueOf(memberId));
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            post.changeAuthor(member);
            postRepository.save(post);
        }
        return postDTO;
    }

    public PostDTO findPost(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setContent(post.getContent());
            return postDTO;
        }
        return null;
    }
}

package ouohoon.doby.comment;

import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDTO save(CommentDTO commentDTO) {
        Comment comment = Comment.builder()
                .content(commentDTO.getContent())
                .build();

        Comment saveComment = commentRepository.save(comment);

        return commentDTO;
    }
}

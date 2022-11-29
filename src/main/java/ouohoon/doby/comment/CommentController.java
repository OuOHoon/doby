package ouohoon.doby.comment;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public String comment(CommentDTO commentDTO) {

        CommentDTO saveDTO = commentService.save(commentDTO);

        return "/post/" + saveDTO.getId().toString();
    }
}

package ouohoon.doby.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * 게시글 저장
     *
     */
    @PostMapping("/{category}")
    public String save() {
        

        return "redirect:/";
    }

    /**
     *
     * @return
     */
    @GetMapping("/{category}")
    public String posts(@PathVariable String category, Integer offset, Integer limit) {

        return "";
    }

    @GetMapping("/{postId}")
    public String post(@PathVariable Long postId,
                       ModelAndView mv) {
        return "ok";
    }

    /**
     * 게시글 수정
     * @param postId
     * @return 수정한 게시글 조회 화면으로 redirect
     */
    @PatchMapping("/{postId}")
    public String edit(@PathVariable Long postId) {
        return "";
    }
}

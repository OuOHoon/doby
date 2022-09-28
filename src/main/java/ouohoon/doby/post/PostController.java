package ouohoon.doby.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {


    /**
     * 게시글 저장
     *
     */
    @PostMapping
    public String save(@ModelAttribute PostSaveForm postSaveForm, Model model) {
        

        return "redirect:/";
    }

    /**
     *
     * @return
     */
    @GetMapping
    public String posts() {

        return "";
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

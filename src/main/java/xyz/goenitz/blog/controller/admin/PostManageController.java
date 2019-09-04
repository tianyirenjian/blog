package xyz.goenitz.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.goenitz.blog.model.Post;
import xyz.goenitz.blog.service.PostManageService;

@Controller
@ControllerAdvice
@RequestMapping("/admin/posts")
public class PostManageController {
    @Autowired
    private PostManageService postManageService;
    @ModelAttribute
    public void addAttributes(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString());
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Posts");
        model.addAttribute("posts", postManageService.getPostList());
        return "admin/posts";
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("title", "New post");
        model.addAttribute("post", new Post());
        return "admin/post-add";
    }

    @PostMapping
    public String store(Post post, RedirectAttributes ra) {
        postManageService.createPost(post);
        ra.addAttribute("created", true);
        return "redirect:/admin/posts";
    }
}

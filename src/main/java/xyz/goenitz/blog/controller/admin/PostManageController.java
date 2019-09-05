package xyz.goenitz.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.goenitz.blog.model.Post;
import xyz.goenitz.blog.service.PostManageService;

import java.util.HashMap;
import java.util.Map;

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
        model.addAttribute("errors", new HashMap<>());
        model.addAttribute("title", "New post");
        model.addAttribute("post", new Post());
        return "admin/post-add";
    }

    @PostMapping
    public String store(@Validated Post post, BindingResult bindingResult, Model model, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError: bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            model.addAttribute("title", "New post");
            model.addAttribute("post", post);
            model.addAttribute("errors", errors);
            return "admin/post-add";
        }
        postManageService.createPost(post);
        ra.addAttribute("created", true);
        return "redirect:/admin/posts";
    }
}

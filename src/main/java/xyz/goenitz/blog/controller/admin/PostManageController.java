package xyz.goenitz.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        model.addAttribute("errors", new HashMap<>());
    }

    @GetMapping
    public String index( Model model, @RequestParam("page") Optional<Integer> page) {
        int currentPage = page.orElse(1);
        model.addAttribute("title", "Posts");
        Page<Post> posts = postManageService.getPostList(currentPage);
        model.addAttribute("posts", posts);
        int totalPage = posts.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "admin/posts";
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("title", "New post");
        model.addAttribute("post", new Post());
        return "admin/post-add";
    }

    @PostMapping
    public String store(@Validated Post post, BindingResult bindingResult, Model model, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            model.addAttribute("title", "New post");
            model.addAttribute("post", post);
            model.addAttribute("errors", errors);
            return "admin/post-add";
        }
        System.out.println(post);
        postManageService.createPost(post);
        ra.addFlashAttribute("action", "created");
        return "redirect:/admin/posts";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable(name = "id") String id, Model model) {
        Post post = postManageService.getPost(id);
        model.addAttribute("title", "Edit post");
        model.addAttribute("post", post);
        return "admin/post-add";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable(name = "id") String id, @Validated Post post, BindingResult bindingResult, Model model, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            model.addAttribute("title", "Edit post");
            model.addAttribute("post", post);
            model.addAttribute("errors", errors);
            return "admin/post-add";
        }
        System.out.println(post);
        postManageService.updatePost(post);
        ra.addFlashAttribute("action", "updated");
        return "redirect:/admin/posts";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(name = "id") String id, RedirectAttributes ra) {
        postManageService.deletePost(id);
        ra.addFlashAttribute("action", "deleted");
        return "redirect:/admin/posts";
    }
}

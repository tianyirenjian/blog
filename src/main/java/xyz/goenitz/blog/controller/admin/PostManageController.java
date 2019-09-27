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
public class PostManageController extends ManageController {
    @Autowired
    private PostManageService postManageService;

    @GetMapping("/admin/posts")
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
                        @RequestParam("keyword") Optional<String> keyword, @RequestParam("tag") Optional<String> tag) {
        model.addAttribute("title", "Posts");
        StringBuilder sb = new StringBuilder();

        int currentPage = page.orElse(1);
        String title = keyword.orElse(null);
        String stag = tag.orElse(null);
        if (title != null) {
            sb.append("&keyword=").append(title);
        }
        model.addAttribute("keyword", title);
        if (stag!= null) {
            sb.append("&tag=").append(stag);
        }
        model.addAttribute("queryString", sb.toString());
        Page<Post> posts = postManageService.getPostList(currentPage, title, stag);
        model.addAttribute("posts", posts);
        int totalPage = posts.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "admin/posts";
    }

    @GetMapping("/admin/posts/create")
    public String create(Model model) {
        model.addAttribute("title", "New post");
        model.addAttribute("post", new Post());
        return "admin/post-add";
    }

    @PostMapping("/admin/posts")
    public String store(@Validated Post post, BindingResult bindingResult, Model model, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "New post");
            model.addAttribute("post", post);
            model.addAttribute("errors", this.getErrors(bindingResult));
            return "admin/post-add";
        }
        postManageService.createPost(post);
        ra.addFlashAttribute("action", "created");
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{id}/edit")
    public String edit(@PathVariable(name = "id") Post post, Model model) {
        model.addAttribute("title", "Edit post");
        model.addAttribute("post", post);
        return "admin/post-add";
    }

    @PostMapping("/admin/posts/{id}/update")
    public String update(@PathVariable(name = "id") String id, @Validated Post post, BindingResult bindingResult, Model model, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Edit post");
            model.addAttribute("post", post);
            model.addAttribute("errors", this.getErrors(bindingResult));
            return "admin/post-add";
        }
        postManageService.updatePost(post);
        ra.addFlashAttribute("action", "updated");
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{id}/delete")
    public String delete(@PathVariable(name = "id") String id, RedirectAttributes ra) {
        postManageService.deletePost(id);
        ra.addFlashAttribute("action", "deleted");
        return "redirect:/admin/posts";
    }

    @GetMapping("admin/tags")
    public String tags(Model model) {
        List<Object> tags = postManageService.getAllTags();
        model.addAttribute("title", "Tags");
        model.addAttribute("tags", tags);
        return "admin/tags";
    }
}

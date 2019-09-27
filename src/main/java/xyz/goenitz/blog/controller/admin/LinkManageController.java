package xyz.goenitz.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.goenitz.blog.model.Link;
import xyz.goenitz.blog.service.LinkManageService;

import java.util.List;

@Controller
public class LinkManageController extends ManageController {
    @Autowired
    private LinkManageService linkManageService;

    @GetMapping("admin/links")
    public String index(Model model) {
        model.addAttribute("title", "Links");
        List<Link> links =  linkManageService.getLinkList();
        model.addAttribute("links", links);
        return "admin/links";
    }

    @GetMapping("admin/links/create")
    public String create(Model model) {
        model.addAttribute("title", "New link");
        model.addAttribute("link", new Link());
        return "admin/link-add";
    }

    @PostMapping("admin/links")
    public String store(@Validated Link link, BindingResult bindingResult, Model model, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "New link");
            model.addAttribute("link", link);
            model.addAttribute("errors", getErrors(bindingResult));
            return "admin/link-add";
        }
        linkManageService.createLink(link);
        ra.addFlashAttribute("action", "created");
        return "redirect:/admin/links";
    }

    @GetMapping("admin/links/{id}/edit")
    public String edit(@PathVariable(name="id") Link link, Model model) {
        model.addAttribute("title", "Edit link");
        model.addAttribute("link", link);
        return "admin/link-add";
    }

    @PostMapping("admin/links/{id}/update")
    public String update(@Validated Link link, BindingResult bindingResult, Model model, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Edit link");
            model.addAttribute("link", link);
            model.addAttribute("errors", getErrors(bindingResult));
            return "admin/link-add";
        }
        linkManageService.updateLink(link);
        ra.addFlashAttribute("action", "updated");
        return "redirect:/admin/links";
    }

    @GetMapping("admin/links/{id}/delete")
    public String delete(@PathVariable(name="id") String id, RedirectAttributes ra) {
        linkManageService.deleteLink(id);
        ra.addFlashAttribute("action", "deleted");
        return "redirect:/admin/links";
    }
}

package xyz.goenitz.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.goenitz.blog.model.Setting;
import xyz.goenitz.blog.service.SettingService;

import java.util.List;
import java.util.Map;

@Controller
public class SettingManageController extends ManageController {
    @Autowired
    private SettingService settingService;

    @GetMapping("/admin/settings")
    public String index(Model model) {
        model.addAttribute("title", "Settings");
        List<Setting> settings = settingService.getAll();
        model.addAttribute("settings", settings);
        return "admin/settings";
    }

    @GetMapping("/admin/settings/create")
    public String create(Model model) {
        model.addAttribute("title", "Add setting");
        model.addAttribute("setting", new Setting());
        return "admin/setting-add";
    }

    @PostMapping("/admin/settings")
    public String store(@Validated Setting setting, BindingResult bindingResult, Model model, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Add Setting");
            model.addAttribute("setting", setting);
            model.addAttribute("errors", getErrors(bindingResult));
            return "admin/setting-add";
        }
        settingService.set(setting);
        ra.addFlashAttribute("action", "created");
        return "redirect:/admin/settings";
    }

    @GetMapping("/admin/settings/{id}/edit")
    public String edit(@PathVariable(name = "id") Setting setting, Model model) {
        model.addAttribute("title", "Edit setting");
        model.addAttribute("setting", setting);
        return "admin/setting-add";
    }

    @PostMapping("/admin/settings/{id}/update")
    public String update(@Validated Setting setting, BindingResult bindingResult, Model model, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Edit setting");
            model.addAttribute("setting", setting);
            model.addAttribute("errors", getErrors(bindingResult));
            return "admin/setting-add";
        }
        settingService.set(setting);
        ra.addFlashAttribute("action", "updated");
        return "redirect:/admin/settings";
    }

    @GetMapping("/admin/settings/{id}/delete")
    public String delete(@PathVariable(name = "id") String id, RedirectAttributes ra) {
        settingService.delete(id);
        ra.addFlashAttribute("action", "deleted");
        return "redirect:/admin/settings";
    }
}

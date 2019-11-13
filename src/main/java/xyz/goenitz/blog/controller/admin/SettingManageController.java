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
        Setting setting = settingService.get();
        model.addAttribute("setting", setting);
        return "admin/settings";
    }

    @PostMapping("/admin/settings")
    public String store(@Validated Setting setting, BindingResult bindingResult, Model model, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Add Setting");
            model.addAttribute("setting", setting);
            model.addAttribute("errors", getErrors(bindingResult));
            return "admin/settings";
        }
        settingService.save(setting);
        ra.addFlashAttribute("action", "saved");
        return "redirect:/admin/settings";
    }
}

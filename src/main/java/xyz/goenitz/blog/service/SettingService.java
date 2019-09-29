package xyz.goenitz.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.goenitz.blog.model.Setting;
import xyz.goenitz.blog.repository.SettingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SettingService {
    @Autowired
    private SettingRepository settingRepository;

    public List<Setting> getAll() {
        return settingRepository.findAll();
    }

    public void set(Setting setting) {
        settingRepository.save(setting);
    }

    public Setting get(String key) {
        Optional<Setting> setting = settingRepository.findById(key);
        return setting.orElseGet(() -> settingRepository.findByKey(key));
    }

    public void delete(String id) {
        settingRepository.deleteById(id);
    }
}

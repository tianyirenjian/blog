package xyz.goenitz.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import xyz.goenitz.blog.model.Setting;
import xyz.goenitz.blog.repository.SettingRepository;

@Service
public class SettingService {
    @Autowired
    private SettingRepository settingRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Setting get() {
        Setting setting = mongoTemplate.findOne(new Query(), Setting.class);
        return setting == null ? new Setting() : setting;
    }

    public void save(Setting setting) {
        settingRepository.save(setting);
    }
}

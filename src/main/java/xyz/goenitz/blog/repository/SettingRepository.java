package xyz.goenitz.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.goenitz.blog.model.Setting;

public interface SettingRepository extends MongoRepository<Setting, String> {
    public Setting findByKey(String key);
}

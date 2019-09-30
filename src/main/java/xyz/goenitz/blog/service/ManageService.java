package xyz.goenitz.blog.service;

import xyz.goenitz.blog.model.Model;

import java.time.Instant;

class ManageService {
    void beforeCreate(Model model) {
        model.setCreated(Instant.now());
        model.setUpdated(Instant.now());
    }

    void beforeUpdate(Model model, Model old) {
        model.setCreated(old.getCreated());
        model.setUpdated(Instant.now());
    }
}

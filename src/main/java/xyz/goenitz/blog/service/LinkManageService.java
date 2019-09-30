package xyz.goenitz.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xyz.goenitz.blog.model.Link;
import xyz.goenitz.blog.repository.LinkRepository;

import java.time.Instant;
import java.util.List;

@Service
public class LinkManageService extends ManageService{
    @Autowired
    private LinkRepository linkRepository;

    public List<Link> getLinkList() {
        return linkRepository.findAll(Sort.by(Sort.Order.desc("sort")));
    }

    public void createLink(Link link) {
        beforeCreate(link);
        linkRepository.save(link);
    }

    public Link getLink(String id) {
        return linkRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Link not found."));
    }

    public void updateLink(Link link) {
        Link old = this.getLink(link.getId());
        beforeUpdate(link, old);
        linkRepository.save(link);
    }

    public void deleteLink(String id) {
        linkRepository.deleteById(id);
    }
}

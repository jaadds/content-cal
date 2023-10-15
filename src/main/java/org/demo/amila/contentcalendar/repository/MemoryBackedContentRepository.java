package org.demo.amila.contentcalendar.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.demo.amila.contentcalendar.model.Content;
import org.demo.amila.contentcalendar.model.Status;
import org.demo.amila.contentcalendar.model.Type;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class MemoryBackedContentRepository {
    
    private final List<Content> contents = new ArrayList<>();

    public List<Content> getAll(){
        return contents;
    }

    public Optional<Content> getContentById(Integer id){
        return contents.stream().filter(c -> c.id().equals(id)).findFirst();
    }


    @PostConstruct
    private void init(){
        Content c = new Content(
            1, 
            "SpringBoot 101", 
            "How to write SpringBoot Applications easily", 
            Status.IDEA, "", Type.ARTICLE, LocalDateTime.now(), null);
        contents.add(c);
    }

    public void save(Content content) {
        contents.add(content);
    }

    public void update(Content content) {
        contents.removeIf(c -> c.id().equals(content.id()));
        contents.add(content);
    }

    public void delete(Integer id) {
        contents.removeIf(c -> c.id().equals(id));
    }

    public boolean contentExists(Integer id) {
        return contents.stream().filter(c -> c.id().equals(id)).count() > 0;
    }

}

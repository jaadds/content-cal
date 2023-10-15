package org.demo.amila.contentcalendar.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import org.demo.amila.contentcalendar.model.Content;
import org.demo.amila.contentcalendar.model.Status;
import org.demo.amila.contentcalendar.repository.ContentRepository;
import org.demo.amila.contentcalendar.repository.JDBCTemplateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    // private final MemoryBackedContentRepository repository;

    // public ContentController(MemoryBackedContentRepository repository) {
    //     this.repository = repository;
    // }

    // private final JDBCTemplateRepository repository;

    // public ContentController(JDBCTemplateRepository repository) {
    //     this.repository = repository;
    // }

    private final ContentRepository repository;

    public ContentController(ContentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content getContentById(@PathVariable("id") Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createContent(@Valid @RequestBody Content content){

        repository.saveAll(Arrays.asList(content));
        // repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateContent(@RequestBody Content content, @PathVariable Integer id){
        if(content.id() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id attribute doesn't match");
        }

        if(!repository.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No content with the specified id");
        }

        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping("/filter/{keyword}")
    public List<Content> filterByTitle(@PathVariable String keyword){
        // return repository.findByTitleContaining(keyword);
        return repository.findByTitleIgnoreCaseContaining(keyword);
    }

    @GetMapping("/filterByStatus/{status}")
    public List<Content> filterByStatus(@PathVariable Status status){
        return repository.listByStatus(status);
    }

}

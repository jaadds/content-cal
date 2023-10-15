package org.demo.amila.contentcalendar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.demo.amila.contentcalendar.model.Content;
import org.demo.amila.contentcalendar.model.Status;
import org.demo.amila.contentcalendar.model.Type;
import org.demo.amila.contentcalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);		
	}

	@Bean
	CommandLineRunner getCommandLineRunner(ContentRepository repository) {
		return args -> {
			Content content = new Content(
            2, 
            "How to use Chat GPT", 
            "Describes how to use Chat GPT",
            Status.IDEA, "", 
            Type.ARTICLE, 
            LocalDateTime.now(), 
            null); 
        
        List<Content> contents = new ArrayList<Content>();
        contents.add(content);

        content =   new Content(
            3, 
            "Azure Functions", 
            "Functions in Azure",
            Status.IDEA, "", 
            Type.VIDEO, 
            LocalDateTime.now(), 
            null); 

        contents.add(content);
        repository.saveAll(contents);
		};
	}
}

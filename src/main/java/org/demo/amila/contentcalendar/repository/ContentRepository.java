package org.demo.amila.contentcalendar.repository;

import java.util.List;

import org.demo.amila.contentcalendar.model.Content;
import org.demo.amila.contentcalendar.model.Status;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {
    
    List<Content> findByTitleContaining(String title);
    List<Content> findByTitleIgnoreCaseContaining(String title);

    @Query(
        """
                SELECT * FROM content 
                where status = :status
                """
    )
    List<Content> listByStatus(@Param("status") Status status);

}

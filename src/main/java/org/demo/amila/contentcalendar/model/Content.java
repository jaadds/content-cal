package org.demo.amila.contentcalendar.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;

public record Content(    

    @Id
    @Column("ID")
    Integer id,
    
    @NotEmpty
    String title,

    @Column("DESCRIPTION")
    String desc,
    Status status,
    String url,

    @Column("TYPE")
    Type contentType,

    @Column("CREATED_AT")
    LocalDateTime createdTime,


    @Column("UPDATED_AT")
    LocalDateTime updatedTime)  implements Persistable<Integer> {

    @Override
    @Nullable
    public Integer getId() {
        // TODO Auto-generated method stub
        return id;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        // TODO Auto-generated method stub
        return updatedTime == null;
    }

}

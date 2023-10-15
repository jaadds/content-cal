package org.demo.amila.contentcalendar.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.demo.amila.contentcalendar.model.Content;
import org.demo.amila.contentcalendar.model.Status;
import org.demo.amila.contentcalendar.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCTemplateRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Content> getAll() {
        return jdbcTemplate.query(
                "select * from content",
                JDBCTemplateRepository::mapRowToCustomer);
    }

    public static Content mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {
        return new Content(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                Status.valueOf(rs.getString("status")),
                rs.getString("url"),
                Type.valueOf(rs.getString("type")),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at") == null ? null : rs.getTimestamp("updated_at").toLocalDateTime());
    }

    public Optional<Content> getContentById(Integer id) {

        List<Content> contents = jdbcTemplate.query(
                "select * from content where id = ?",
                JDBCTemplateRepository::mapRowToCustomer, id);

        if (contents.size() > 0)
            return Optional.of(contents.get(0));
        else
            return Optional.empty();
    }

    public void save(Content content) {
       int rowNum = jdbcTemplate.update("insert into content (id, title, description, status, url, type, created_at, updated_at) values (?, ?, ?, ?, ?, ?, ?, ?)",
                content.id(),
                content.title(),
                content.desc(),
                content.status().toString(),
                content.url(),
                content.contentType().toString(),
                content.createdTime(),
                content.updatedTime());
    }

    public boolean update(Content content) {
        return jdbcTemplate.update("update content set title = ?, description = ?, status = ?, url = ?, type = ?, updated_at = ? where id = ?",
                content.title(),
                content.desc(),
                content.status().toString(),
                content.url(),
                content.contentType().toString(),
                content.updatedTime(),
                content.id()) > 0;
    }

    public void delete(Integer id) {
        jdbcTemplate.update("delete from content where id = ?", id);
    }

    public boolean contentExists(Integer id) {
        return this.getContentById(id).isPresent();
    }

}

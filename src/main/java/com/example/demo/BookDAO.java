package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BookDAO {
    private JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM books", (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setLoaned(rs.getBoolean("isLoaned"));
            return book;
        });
    }

    // Additional methods for insert, update, delete
}

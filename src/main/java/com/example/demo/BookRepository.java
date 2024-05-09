package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addBook(String title, String author) {
        jdbcTemplate.update("INSERT INTO books (title, author, isLoaned) VALUES (?, ?, false)", title, author);
    }

    public List<Book> listBooks() {
        return jdbcTemplate.query("SELECT * FROM books", bookRowMapper());
    }

    public void updateLoanStatus(Long id, boolean status) {
        jdbcTemplate.update("UPDATE books SET isLoaned = ? WHERE id = ?", status, id);
    }

    private RowMapper<Book> bookRowMapper() {
        return (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setLoaned(rs.getBoolean("isLoaned"));
            return book;
        };
    }
}

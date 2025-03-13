package service.custome.impl;

import dto.Book;
import entity.BookEntity;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.modelmapper.ModelMapper;
import repository.custome.BookDao;
import repository.custome.impl.BookDaoImpl;
import service.custome.BookService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> getAll() {
        List<Book> bookList = new ArrayList<>();
        bookDao.getAll().forEach(entity -> bookList.add(new ModelMapper()
                .map(entity, Book.class)));
        return bookList;
    }

    @Override
    public boolean updateBook(Book book) {
        return bookDao.update(new ModelMapper()
                .map(book, BookEntity.class));
    }

    @Override
    public boolean addBook(Book book) {
        return bookDao.save(new ModelMapper()
                .map(book, BookEntity.class));
    }

    @Override
    public boolean delete(Book book) {
        return bookDao.delete(new ModelMapper()
                .map(book, BookEntity.class));
    }

    @Override
    public List<String> getAllAuthor() {
        List<String> authorList = new ArrayList<>();
        for (Book book : getAll()) {
            if (authorList.isEmpty()) {
                authorList.add(book.getAuthor());
            }else {
                String exitAuthor="";
                for (String author:authorList){
                    if (book.getAuthor().equals(author)){
                        exitAuthor=author;
                    }
                }
                if (exitAuthor.isBlank()){
                    authorList.add(book.getAuthor());
                }
            }
        }
        return authorList;
    }

    @Override
    public List<Book> searchByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return Collections.emptyList(); // Return empty list if title is null or empty
        }

        String userInputTitle = title.toLowerCase();
        LevenshteinDistance levenshtein = new LevenshteinDistance();

        return getAll().stream()
                .filter(book -> {
                    String bookTitle = Optional.ofNullable(book.getTitle()).orElse("").toLowerCase();
                    return bookTitle.contains(userInputTitle) || levenshtein.apply(userInputTitle, bookTitle) <= 2;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Book searchByID(String id) {
        return getAll().stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            return Collections.emptyList();
        }

        String userInputAuthor = author.toLowerCase();
        LevenshteinDistance levenshtein = new LevenshteinDistance();

        return getAll().stream()
                .filter(book -> {
                    if (book.getAuthor() == null) return false;
                    String bookAuthor = book.getAuthor().toLowerCase();

                    // Exact or close match
                    int distance = levenshtein.apply(userInputAuthor, bookAuthor);
                    return bookAuthor.contains(userInputAuthor) || distance <= 2;
                })
                .toList();
    }
}

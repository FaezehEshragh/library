package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.exception.BookNotFoundException;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {

    BookRepository bookRepository;

    @Autowired
    private void setTransformerService( BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public List<Book> getAllBooks() {
        List<Book> bookList=new ArrayList<Book>();
        Iterable<Book> booksIterable=bookRepository.findAll();
//        bookList= StreamSupport.stream(booksIterable.spliterator(), false)
//                .collect(Collectors.toList());
        booksIterable.forEach(bookList::add);
        return bookList;
    }

    public Book findById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book save(Book newBook) {
        return bookRepository.save(newBook);
    }

    public Book update(Book newBook, String id) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setName(newBook.getName());
                    book.setAuthor(newBook.getAuthor());
                    book.setAvailable(newBook.getAvailable());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return bookRepository.save(newBook);
                });

    }

    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }
}

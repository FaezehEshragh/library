package com.example.library.controller;


import com.example.library.domain.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    BookService bookService;

    @Autowired
    private void setTransformerService( BookService bookService){
        this.bookService=bookService;
    }


    @GetMapping("/list")
    List<Book> listAllBooks(){
        return bookService.getAllBooks();

    }

    @GetMapping("/list/{id}")
    Book getBookById(@PathVariable String id){
        return  bookService.findById(id);
    }


    @PostMapping("/list")
    Book saveBook(@RequestBody Book newBook) {
        return bookService.save(newBook);
    }


    @PutMapping("/list/{id}")
    Book replaceBook(@RequestBody Book newBook, @PathVariable String id) {

        return bookService.update(newBook,id);
    }

    @DeleteMapping("/list/{id}")
    void deleteBook(@PathVariable String id) {
        bookService.deleteById(id);
    }



}

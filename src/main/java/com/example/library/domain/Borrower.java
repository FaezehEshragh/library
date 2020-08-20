package com.example.library.domain;


import com.example.library.repository.BookRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Data
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;

    String Name;
    Boolean blocked;
    int numberOfAllowedBooks;


// cause book can exist without a borrower I didn't use ManyToOne in book class
    @OneToMany(mappedBy = "borrower", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    List<Book> borrowedBooks;



    Book borrowBook(Book book){
        if(!blocked && borrowedBooks.size()<numberOfAllowedBooks) {
              if(book.available) {
                  book.available=false;
                  borrowedBooks.add(book);
              }

        }
        return book;
    }

    Book returnBook(Book book){
        if(borrowedBooks.contains(book)){
            book.available=true;
            borrowedBooks.remove(book);
        }
        return book;
    }


}

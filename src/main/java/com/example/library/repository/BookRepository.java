package com.example.library.repository;

import com.example.library.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book,String> {


}

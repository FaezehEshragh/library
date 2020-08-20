package com.example.library.repository;


import com.example.library.domain.Borrower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends CrudRepository<Borrower,String> {
}

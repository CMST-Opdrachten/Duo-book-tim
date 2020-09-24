package com.example.demo.repositorys;

import com.example.demo.objects.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BookRepo extends JpaRepository<Book, BigInteger> {


}

package com.datajpa.jpademo;

import com.datajpa.jpademo.domain.Book;
import com.datajpa.jpademo.domain.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JapdemoApplicationTests {
    @Autowired
    BookRepository bookRepository;

    @Test
    public void contextLoads() {

        Book book = new Book();
        book.setName("零四");
        bookRepository.save(book);
    }

}

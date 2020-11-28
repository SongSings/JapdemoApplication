package com.datajpa.jpademo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Book 数据持久层操作接口
 *
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByName(String name);
}

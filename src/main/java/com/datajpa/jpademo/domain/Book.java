package com.datajpa.jpademo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.annotation.Native;
import java.lang.reflect.Constructor;

/**
 * Book 实体类
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Book")
public class Book implements Serializable {

    /**
     * 编号
     */
    @Id
    //@GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 书名
     */
    public String name;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", writer='" + writer + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }

    /**
     * 作者
     */
    private String writer;

    /**
     * 简介
     */
    private String introduction;

    public void eat(){
        System.out.println("Book.eat");
    }
    public void eat(String val){
        System.out.println("Book.eat" + val);
    }
    private void talk(String val){
        System.out.println("Book.talk" + val);
    }

}

package com.ToDoApi.todo.databases.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "kanghyuk")
@Entity
public class ToDoList {
    @Id
    private Integer id;
    private String contents;
}

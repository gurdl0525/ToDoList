package com.ToDoApi.todo.databases.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Table(name = "todolist")
@Entity
public class ToDoList {
    @Id
    private Long id;
    private String contents;
}

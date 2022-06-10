package com.ToDoApi.todo.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todolist")
@Entity
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contents;
    @Column(name = "is_success") //, columnDefinition = "tinyint(1) default 0")
    private Boolean is_success;
}

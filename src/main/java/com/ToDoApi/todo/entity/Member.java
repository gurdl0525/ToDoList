package com.ToDoApi.todo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "member_id")
    private String accountId;
    @Column(name = "member_password")
    private String password;
    @OneToMany(mappedBy = "member")
    private List<ToDoList> toDoLists;
}

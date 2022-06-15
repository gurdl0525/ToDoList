package com.ToDoApi.todo.repository;

import com.ToDoApi.todo.entity.Member;
import com.ToDoApi.todo.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
    List<ToDoList> findByMember(Member member);
}

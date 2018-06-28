package com.epam.todo.repository;

import com.epam.todo.entity.Task;
import com.epam.todo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByTagAndAuthor(String tag, User user);
    List<Task> findByAuthor(User user);
}

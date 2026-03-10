package com.example.taskapp.repository;

import com.example.taskapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/*Taskエンティティ用のリポジトリ
CRUD操作を自動生成 */

public interface TaskRepository extends JpaRepository<Task, Long> {
}
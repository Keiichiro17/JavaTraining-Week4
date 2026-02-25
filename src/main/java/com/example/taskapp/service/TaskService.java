package com.example.taskapp.service;
import com.example.taskapp.entity.Task;
import com.example.taskapp.exception.TaskNotFoundException;
import com.example.taskapp.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/*タスクを管理するサービスクラス */
@Service
@Transactional
public class TaskService{

    //データベース操作用のリポジトリ
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

//タスク一覧の取得 

@Transactional(readOnly = true)
    public List<Task> findAll(){
        return repo.findAll();
    }

//新しいタスクを登録する

    public Task save(Task task){
        return repo.save(task);
    }

/*指定のIDのタスクを更新する
IDが存在しない場合はエラーを表示 */

public Task update (Long id, Task updated){
//指定IDのタスクを取得（存在しない場合はエラー404を表示）

    Task existing=repo.findById(id).orElseThrow(()->new TaskNotFoundException(id));
    //登録したタスクの値を更新

    existing.setTitle(updated.getTitle());
    existing.setCompleted(updated.isCompleted());
    //終了時に自動でUPDATEされる（永続化）
    
    return existing;//
}

//指定のIDのタスクを削除する
public void delete(Long id){
    //削除対象を取得
    Task existing=repo.findById(id).orElseThrow(()->new TaskNotFoundException(id));
    //削除の実行
    repo.delete(existing);
}
}
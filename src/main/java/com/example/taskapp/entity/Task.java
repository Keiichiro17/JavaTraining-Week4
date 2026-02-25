package com.example.taskapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



//タスク情報を管理するエンティティクラス

@Entity
public class Task{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

/*タスクのタイトル　
 空白不可、最大50文字まで*/

    @NotBlank(message="タイトルを入力してください")
    @Size(max=50,message="タイトルは50文字以内で入力してください")
    private String title;
    
    /*完了フラグ
    true:完了、false:未完了と表示 */
    
    private boolean completed=false;

//GetterとSetterの導入

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

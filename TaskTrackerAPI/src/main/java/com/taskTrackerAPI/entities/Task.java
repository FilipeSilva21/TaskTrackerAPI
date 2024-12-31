package com.taskTrackerAPI.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tbTasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskId")
    private Long Id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    public Task() {
    }

    public Task(Long id, String title, String description) {
        Id = id;
        this.title = title;
        this.description = description;
    }
}

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
    private Long taskId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "isDone")
    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "userId")
    public User user;

    public Task() {
    }

    public Task(Long taskId, String name, String description, Boolean isDone, User user) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.isDone = isDone;
    }
}

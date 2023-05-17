package pl.agntyp.zadanie25;

import jakarta.persistence.*;
import org.springframework.boot.convert.DurationFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class TaskDuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private String duration;

    @OneToOne(mappedBy = "taskDuration")
    private Task task;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}

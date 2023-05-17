package pl.agntyp.zadanie25;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    private boolean done = false;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "duration_id", referencedColumnName = "id")
    private TaskDuration taskDuration;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public TaskDuration getTaskDuration() {
        return taskDuration;
    }

    public void setTaskDuration(TaskDuration taskDuration) {
        this.taskDuration = taskDuration;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

package pl.agntyp.zadanie25;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "duration_id", referencedColumnName = "id")
//    private TaskDuration taskDuration;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public String showEndTime() {
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
        if (endTime != null) {
            return datePattern.format(endTime);
        }
        return "-";
    }

    public String showStartTime() {
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
        if (startTime != null) {
            return datePattern.format(startTime);
        }
        return "-";
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

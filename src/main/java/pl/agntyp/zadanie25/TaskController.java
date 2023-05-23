package pl.agntyp.zadanie25;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;

    private final CategoryRepository categoryRepository;

    public TaskController(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Task> tasks = taskRepository.findByDoneIsFalseOrderByDueDateAsc();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("categories", categories);
        model.addAttribute("newTask", new Task());
        model.addAttribute("newCategory", new Category());

        return "home";
    }

    @GetMapping("/archive")
    public String archive(Model model) {
        List<Task> tasks = taskRepository.findByDoneIsTrueOrderByDueDateDesc();
        Map<Task, String> taskDurations = new LinkedHashMap<>();
        tasks.forEach(task -> {
            String durationFormatted = "-";
            if (task.getStartTime() != null) {
                Duration duration = Duration.between(task.getStartTime(), task.getEndTime());
                durationFormatted = String.format("%02d:%02d:%02d",
                        duration.toHoursPart(),
                        duration.toMinutesPart(),
                        duration.toSecondsPart()
                );
            }
            taskDurations.put(task, durationFormatted);
        });
        model.addAttribute("tasks", taskDurations);

        return "archive";
    }

    @GetMapping("/edit")
    public String editTask(@RequestParam long id, Model model) {
        Task taskToEdit = taskRepository.findById(id).orElse(null);
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("task", taskToEdit);
        model.addAttribute("categories", categories);
        return "edit";
    }

    @PostMapping("/edit")
//    @Transactional
    public String edit(Task task) {
        task.setTitle(task.getTitle());
        task.setDescription(task.getDescription());
        task.setCategory(task.getCategory());
        task.setDueDate(task.getDueDate());
        task.setDone(task.isDone());
        task.setStartTime(task.getStartTime());
        task.setEndTime(task.getEndTime());
//        task.setDone(task.isDone());
        taskRepository.save(task);

        return "redirect:/";
    }

    @PostMapping("/add")
    public String addTask(Task task) {

        taskRepository.save(task);
        return "redirect:/";
    }

    @PostMapping("/addCategory")
    public String addTask(Category category) {
        if (categoryRepository.findByName(category.getName()) == null) {
            categoryRepository.save(category);
        }
        return "redirect:/";
    }

    @PostMapping("/done")
    public String taskDone(Task task) {
        Task taskDone = taskRepository.findById(task.getId()).orElse(null);
        if (taskDone != null) {
            taskDone.setEndTime(LocalDateTime.now());
            taskDone.setDone(true);
            taskRepository.save(taskDone);
        }
        return "redirect:/archive";
    }

    @PostMapping("/startTask")
    public String startTask(Task task) {
        Task taskToStart = taskRepository.findById(task.getId()).orElse(null);
        if (taskToStart != null && taskToStart.getStartTime() == null) {
            taskToStart.setStartTime(LocalDateTime.now());
            taskRepository.save(taskToStart);
        }
        return "redirect:/";
    }

    @PostMapping("/completeTask")
    public String completeTask(Task task) {
        Task taskToComplete = taskRepository.findById(task.getId()).orElse(null);
        if (taskToComplete != null) {
            taskToComplete.setEndTime(LocalDateTime.now());
            taskToComplete.setDone(true);
            taskRepository.save(taskToComplete);
        }
        return "redirect:/archive";
    }

}

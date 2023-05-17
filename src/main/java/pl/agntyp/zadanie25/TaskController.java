package pl.agntyp.zadanie25;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskDurationRepository taskDurationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
        model.addAttribute("tasks", tasks);

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
        boolean categoryFound = false;
        List<Category> categories = categoryRepository.findAll();
        for (Category cat : categories) {
            if (cat.getName().equals(category.getName())) {
                categoryFound = true;
                break;
            }
        }
        if (!categoryFound) {
            categoryRepository.save(category);
        }
        return "redirect:/";
    }

    @PostMapping("/done")
    public String taskDone(Task task) {
        Task taskDone = taskRepository.findById(task.getId()).orElse(null);
        if (taskDone != null) {
            taskDone.setDone(true);
            taskRepository.save(taskDone);
        }
        return "redirect:/";
    }

}

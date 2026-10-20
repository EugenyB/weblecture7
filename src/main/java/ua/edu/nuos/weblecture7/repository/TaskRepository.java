package ua.edu.nuos.weblecture7.repository;

import org.springframework.stereotype.Repository;
import ua.edu.nuos.weblecture7.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {
    private List<Task> tasks = new CopyOnWriteArrayList<>();
    private AtomicLong idGen = new AtomicLong(1);

    public List<Task> findAll() {
        return tasks.stream().toList();
    }

    public Task findById(Long id) {
        return tasks.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public Task save(Task task) {
        if (task.getId() == null || task.getId() == 0L) {
            task.setId(idGen.incrementAndGet());
        }
        tasks.removeIf(t -> t.getId().equals(task.getId()));
        tasks.add(task);
        return task;
    }

}



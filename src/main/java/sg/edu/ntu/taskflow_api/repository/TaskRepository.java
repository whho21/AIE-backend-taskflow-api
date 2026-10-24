package sg.edu.ntu.taskflow_api.repository;

import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import sg.edu.ntu.taskflow_api.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TaskRepository {
    private final Map<Long, Task> tasks = new HashMap<>();
    private long nextId = 1L;

    public Task save(Task task) {
        task.setId(nextId++);
        tasks.put(task.getId(), task);
        return task;
    }

    public Task update(Task task) {
        Long id = task.getId();
        if (id == null || !tasks.containsKey(id)) {
            throw new IllegalArgumentException("Cannot update a task that is not in the repository");
        }
        tasks.put(id, task);
        return task;
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public void delete(Task task) {
        tasks.remove(task.getId());
    }
}

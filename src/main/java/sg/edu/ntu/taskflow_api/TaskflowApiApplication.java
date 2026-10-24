package sg.edu.ntu.taskflow_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.ntu.taskflow_api.model.Task;
import sg.edu.ntu.taskflow_api.service.TaskService;

@SpringBootApplication
public class TaskflowApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskflowApiApplication.class, args);
	}

	@Bean
	CommandLineRunner loadSampleTasks(TaskService taskService) {
		return args -> {
			if (!taskService.findAllTasks().isEmpty()) {
				return;
			}

			taskService.createTask(createTask("Plan weekly priorities",
					"Review goals and choose the most important tasks for this week.", false));
			taskService.createTask(createTask("Update project documentation",
					"Add setup steps and API examples to the project documentation.", false));
			taskService.createTask(createTask("Review pull requests",
					"Check open changes and leave feedback for the team.", true));
			taskService.createTask(createTask("Prepare meeting notes",
					"Write up decisions and action items from the team meeting.", true));
			taskService.createTask(createTask("Organize workspace",
					"Clear the desk and organize project files.", false));
		};
	}

	private static Task createTask(String title, String description, boolean completed) {
		Task task = new Task();
		task.setTitle(title);
		task.setDescription(description);
		task.setCompleted(completed);
		return task;
	}

}


package com.example.ToDoList;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.ToDoList.domain.Category;
import com.example.ToDoList.domain.Task;
import com.example.ToDoList.domain.TaskRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {

	@Autowired
	private TaskRepository repository;

	@Test
	public void findByTasknameShouldReturnImportance() {
		List<Task> tasks = repository.findByTaskname("Walk the dog");

		assertThat(tasks).hasSize(1);
		assertThat(tasks.get(0).getImportance()).isEqualTo("3");
	}

	@Test
	public void createNewTask() {
		Task task = new Task("Call mom", "3", "Doing", new Category("Family & friends"));
		repository.save(task);
		assertThat(task.getId()).isNotNull();
	}

}

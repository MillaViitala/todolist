package com.example.ToDoList;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ToDoList.domain.Category;
import com.example.ToDoList.domain.CategoryRepository;
import com.example.ToDoList.domain.Task;
import com.example.ToDoList.domain.TaskRepository;
import com.example.ToDoList.domain.User;
import com.example.ToDoList.domain.UserRepository;

@SpringBootApplication
public class ToDoListApplication {
	private static final Logger log = LoggerFactory.getLogger(ToDoListApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}

	@Bean
	public CommandLineRunner taskDemo(TaskRepository trepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("Save tasks");
			crepository.save(new Category("Home"));
			crepository.save(new Category("Shool"));
			crepository.save(new Category("Hobbies"));
			crepository.save(new Category("Work"));

			trepository.save(new Task("Walk the dog", "3", "DONE", crepository.findByName("Home").get(0)));
			trepository.save(new Task("Go to dance class", "2", "DONE",crepository.findByName("Hobbies").get(0)));
			
			User user1 = new User("user","$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin","$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("Get all tasks");
			for (Task task : trepository.findAll()) {
				log.info(task.toString());
			}

		};
	}
}


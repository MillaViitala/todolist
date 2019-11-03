package com.example.ToDoList.web;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ToDoList.domain.CategoryRepository;
import com.example.ToDoList.domain.Task;
import com.example.ToDoList.domain.TaskRepository;

@Controller
public class TaskController {
	@Autowired
	private TaskRepository trepository;

	@Autowired
	private CategoryRepository crepository;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/tasklist")
	public String taskList(Model model) {
		model.addAttribute("tasks", trepository.findAll());
		return "tasklist";
	}

	@RequestMapping(value = "/add")
	public String addTask(Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("categories", crepository.findAll());
		return "addtask";
	}

	// REST
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public @ResponseBody List<Task> taskListRest() {
		return (List<Task>) trepository.findAll();
	}

	// REST id
	@RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Task> findTaskRest(@PathVariable("id") Long taskId) { //findStudentRest?
		return trepository.findById(taskId);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Task task) {
		trepository.save(task);
		return "redirect:tasklist";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteTask(@PathVariable("id") Long taskId, Model model) {
		trepository.deleteById(taskId);
		return "redirect:../tasklist";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String addTask(@PathVariable("id") Long taskId, Model model) {
		model.addAttribute("categories", crepository.findAll());
		model.addAttribute("task", trepository.findById(taskId));
		return "edittask";
	}

}


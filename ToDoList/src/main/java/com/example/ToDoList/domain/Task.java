package com.example.ToDoList.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String taskname, importance, complete;
	

	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;

	public Task() {

	}

	public Task(String taskname, String importance, String complete, Category category) {
		super();
		this.taskname = taskname;
		this.importance = importance;
		this.complete = complete;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		if (this.category != null)
			return "Task [id=" + id + ", taskname=" + taskname + ", importance=" + importance + ", complete=" + complete + " category =" + this.getCategory() + "]";
		else
			return "Task [id=" + id + ", taskname=" + taskname + ", importance=" + importance + ", complete=" + complete + "]";
	}
}


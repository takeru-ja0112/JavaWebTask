package DAO;

public class Task {
	private int taskId;
	private String userName;
	private String taskName;
	private String status;
	private String created_at;
	
	public Task(int taskId, String userName, String taskName, String status,String created_at ) {
		this.taskId = taskId;
		this.userName = userName;
		this.taskName = taskName;
		this.status = status;
		this.created_at = created_at;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	public String FormattedDate() {
		String formattedDate = created_at.substring(0,10);
		return formattedDate;
	}


	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}


	public int getTaskId() {
		return taskId;
	}


	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
}

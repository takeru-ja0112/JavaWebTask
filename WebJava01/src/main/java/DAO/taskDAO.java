package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class taskDAO {
	private Connection con;

	public taskDAO(Connection con) {
		this.con = con;
	}

	public List<Task> getTaskByUserId(String userId) throws SQLException {
		List<Task> tasks = new ArrayList<>();
		String sql = "SELECT * FROM task WHERE username = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Task task = new Task(
						rs.getString("username"),
						rs.getString("taskname"),
						rs.getString("status"),
						rs.getString("created_at"));

				tasks.add(task);
			}
		}
		return tasks;

	}
	
	public void registTask(String sessionName,String taskVlue) {
		String sql = "INSERT INTO task (username , taskname) VALUES (?,?) ";
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, sessionName);
			ps.setString(2, taskVlue);
			
			int count = ps.executeUpdate();
			System.out.println(count + "件の更新が完了しました");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}

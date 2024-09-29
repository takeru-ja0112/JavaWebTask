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

	//	タスク一覧用取得
	public List<Task> getTaskByUserId(String userId, int recordsPerPage, int start, String status) throws SQLException {
		List<Task> tasks = new ArrayList<>();
//		String sql = "SELECT * FROM task WHERE username = ? ORDER BY task_id LIMIT ? OFFSET ?";
		StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM task WHERE username = ? ");
		switch (status) {
		case "すべて":break;
		case "未完了":sqlBuilder.append("AND status = '" + status +"'");break;
		case "完了":sqlBuilder.append("AND status = '" + status + "'");break;
		}
		sqlBuilder.append(" ORDER BY task_id LIMIT ? OFFSET ?");

		try (PreparedStatement ps = con.prepareStatement(sqlBuilder.toString())) {
			ps.setString(1, userId);
			ps.setInt(2, recordsPerPage);
			ps.setInt(3, start);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Task task = new Task(
						rs.getInt("task_id"),
						rs.getString("username"),
						rs.getString("taskname"),
						rs.getString("status"),
						rs.getString("created_at"));

				tasks.add(task);
			}
		}
		return tasks;

	}

	//	タスク総数抽出
	public int getTotalTask(String userId ,String status) throws SQLException {

//		String sql = "SELECT COUNT(*) FROM task WHERE username = ?";
		StringBuilder sqlBuilder = new StringBuilder("SELECT COUNT(*) FROM task WHERE username = ? ");
		switch (status) {
		case "すべて":break;
		case "未完了":sqlBuilder.append("AND status = '" + status +"'");break;
		case "完了":sqlBuilder.append("AND status = '" + status + "'");break;
		}
		try (PreparedStatement ps = con.prepareStatement(sqlBuilder.toString())) {
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}

		}
		return 0;
	}

	//	タスク登録
	public void registTask(String sessionName, String taskVlue) {
		String sql = "INSERT INTO task (username , taskname) VALUES (?,?) ";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, sessionName);
			ps.setString(2, taskVlue);

			int count = ps.executeUpdate();
			System.out.println(count + "件の更新が完了しました");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//	タスク削除
	public void deleteTask(int taskId) {
		String sql = "DELETE FROM task WHERE task_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, taskId);

			int count = ps.executeUpdate();
			System.out.println(count + "件の削除が完了しました");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//	タスク取得
	public String getTaskValues(int taskId) {
		String taskValue = null;
		String sql = "SELECT taskname FROM task WHERE task_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, taskId);

			ResultSet rs = ps.executeQuery();
			rs.next();
			taskValue = rs.getString("taskname");
			return taskValue;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskValue;
	}

	//	タスクの更新
	public void editTask(String inputTaskValue, int taskId) {
		String sql = "UPDATE task SET taskname = ? WHERE task_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, inputTaskValue);
			ps.setInt(2, taskId);

			int count = ps.executeUpdate();
			System.out.println(count + "件の更新が完了しました");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//	ステータス変更
	public void okTask(int taskId) {
		String sql = "UPDATE task SET status =? WHERE task_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, "完了");
			ps.setInt(2, taskId);

			int count = ps.executeUpdate();
			System.out.println(count + "件の更新が完了しました");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

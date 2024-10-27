package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import security.PassHash;

public class userDAO {
	private Connection con = null;

	//接続
	public void connect() {
		try {
			con = Database.getConnection();
			System.out.println("接続完了しました");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String select(int userId) {
		Statement stmt = null;
		ResultSet rs = null;
		String result = null;
		String sql = "select name from users where id = " + userId;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			result = rs.getString("name");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	//接続解除
	public void disconnect() {
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//ログイン処理
	public boolean validateLogin(String inputUserId, String inputPassword) {
		boolean isValid = false;
		PassHash hashPassword = new PassHash();
		String password = hashPassword.hashPassword(inputPassword);
		

		String sql = "SELECT password FROM users WHERE name = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, inputUserId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String userPassword = rs.getString("password");

					if (userPassword.equals(password)) {
						isValid = true;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValid;
	}
	
//	新規ユーザ登録処理
	public void regist(String userId,String userPassword,String email) {
		String sql = "INSERT INTO users (name, password, email) VALUES (?,?,?)";
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1,userId);
			ps.setString(2, userPassword);
			ps.setString(3, email);
			
			int count = ps.executeUpdate();
			System.out.println(count + "件の更新が完了しました。");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	仮登録用ユーザ登録処理
	public void regist_tmp(String uuid, String userId,String userPassword, String email) {
		String sql = "INSERT INTO users_tmp (uuid, name , password,email) VALUES(?::uuid,?,?,?)";
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, uuid);
			ps.setString(2, userId);
			ps.setString(3, userPassword);
			ps.setString(4, email);
			
			int count = ps.executeUpdate();
			System.out.println(count + "件の更新が完了しました。");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	UUIDからのデータベースへの登録
	public void uuidRegist(String inputuuid) {
		String sql = "INSERT INTO users (name , password , email) SELECT name , password , email FROM users_tmp WHERE uuid = ?";
		
		UUID uuid = UUID.fromString(inputuuid);
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setObject(1, uuid);
			
			int count = ps.executeUpdate();
			System.out.println(count + "件のテーブルへの登録が完了しました。");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	仮DBのメールアドレス修正
	public void changeEmail(String inputuuid, String newEmail) {
		String sql = "UPDATE users_tmp SET email = ? WHERE uuid = ?";
		
		UUID uuid = UUID.fromString(inputuuid);
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, newEmail);
			ps.setObject(2, uuid);
			
			int count = ps.executeUpdate();
			System.out.println(count + "件のメールアドレスの更新が完了しました。");
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
//	UUIDから対応する行の削除
	public void uuidDelete(String inputuuid) {
		String sql = "DELETE FROM users_tmp WHERE uuid = ?";
		
		UUID uuid = UUID.fromString(inputuuid);
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setObject(1, uuid);
			
			int count = ps.executeUpdate();
			System.out.println(count + "件の削除が完了しました\n\n" + uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	名前が重複していないか
	public boolean userCheck(String userId) {
		String sql = "SELECT COUNT(*) FROM users WHERE name = ?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, userId);
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					int count = rs.getInt(1);
					return count > 0;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean EmailCheck(String Email) {
		String sql = "SELECT COUNT(*) FROM users WHERE email  = ?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, Email);
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					int count = rs.getInt(1);
					return count > 0;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
//	メールアドレスからIDの取得
	public int EmailIdCheck(String Email) {
		int id = 0;
		String sql = "SELECT id FROM users WHERE email = ?";
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, Email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getInt(1);
				return id;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
//	IDが一致するアカウントのパスワードの変更
	public void ChengePassword(int id , String pass) {
		String sql = "UPDATE users SET password = ? WHERE id = ?";
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, pass);
			ps.setInt(2,id);
			
			int count = ps.executeUpdate();
			System.out.println(count + "件の更新が完了しました");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

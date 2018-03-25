package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Solution {
	private int id;
	private String created;
	private String updated;
	private String description;
	private int exerciseId;
	private int userId;
	
	
	
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		
		this.description = description;
	}
	public int getId() {
		return id;
	}

	public void saveToDB(Connection conn) throws SQLException {
		  if (this.id == 0) {
		    String sql = "INSERT INTO Solutions(created, updated, description) VALUES (?, ?, ?)";
		    String generatedColumns[] = { "ID" };
		    PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql, generatedColumns);
		    preparedStatement.setString(1, this.created);
		    preparedStatement.setString(2, this.updated);
		    preparedStatement.setString(3, this.description);
		    preparedStatement.executeUpdate();
		    ResultSet rs = preparedStatement.getGeneratedKeys();
		    if (rs.next()) {
		      this.id = rs.getInt(1);
		    }
		  }else {
			    String sql = "UPDATE Solutions SET username=?, email=?, password=? where id = ?";
			    PreparedStatement preparedStatement;
			    preparedStatement = conn.prepareStatement(sql);
			    preparedStatement.setString(1, this.created);
			    preparedStatement.setString(2, this.updated);
			    preparedStatement.setString(3, this.description);
			    preparedStatement.setInt(4, this.id);
			    preparedStatement.executeUpdate();
			}
		}
	
		public void delete(Connection conn) throws SQLException {
		    if (this.id != 0) {
		        String sql = "DELETE FROM Solutons WHERE id= ?";
		        PreparedStatement preparedStatement;
		        preparedStatement = conn.prepareStatement(sql);
		        preparedStatement.setInt(1, this.id);
		        preparedStatement.executeUpdate();
		        this.id=0;
		    }
		}

		static public Solution[] loadAll(Connection conn) throws SQLException {
		    ArrayList<Solution> solution = new ArrayList<Solution>();
		    String sql = "SELECT * FROM Solutions"; PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql);
		    ResultSet resultSet = preparedStatement.executeQuery();
		    while (resultSet.next()) {
		    	Solution loadedSolution = new Solution();
		        loadedSolution.id = resultSet.getInt("id");
		        loadedSolution.created = resultSet.getString("created");
		        loadedSolution.updated = resultSet.getString("updated");
		        loadedSolution.description = resultSet.getString("description");
		        solution.add(loadedSolution);
		        }
		    Solution[] uArray = new Solution[solution.size()];
		    uArray = solution.toArray(uArray);
		    return uArray;    
			}

		static public Solution loadById(Connection conn, int id) throws SQLException {
		    String sql = "SELECT * FROM Solutions where id=?";
		    PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql);
		    preparedStatement.setInt(1, id);
		    ResultSet resultSet = preparedStatement.executeQuery();
		    if (resultSet.next()) {
		    	Solution loadedSolution = new Solution();
		        loadedSolution.id = resultSet.getInt("id");
		        loadedSolution.created = resultSet.getString("created");
		        loadedSolution.updated = resultSet.getString("updated");
		        loadedSolution.description = resultSet.getString("description");
		        return loadedSolution;
		        }
		    return null;
		}
		
		static public Solution[] loadAllByUserId(Connection conn) throws SQLException {
			ArrayList<Solution> solution = new ArrayList<>();
			String sql = "SELECT * FROM Solutions WHER user_id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
		    ResultSet resultSet = preparedStatement.executeQuery();
		    while (resultSet.next()) {
		    	Solution loadedSolution = new Solution();
		        loadedSolution.id = resultSet.getInt("id");
		        loadedSolution.created = resultSet.getString("created");
		        loadedSolution.updated = resultSet.getString("updated");
		        loadedSolution.description = resultSet.getString("description");
		        loadedSolution.exerciseId = resultSet.getInt("exercise_id");
		        loadedSolution.userId = resultSet.getInt("user_id");
		        solution.add(loadedSolution);
		        }
		    Solution[] uArray = new Solution[solution.size()];
		    uArray = solution.toArray(uArray);
		    return uArray;    		
		}
		
		static public Solution[] loadAllByExerciseId (Connection conn) throws SQLException {
			ArrayList<Solution> solution = new ArrayList<>();
			String sql = "SELECT * FROM solutions WHERE exercise_id = ? ORDER BY created ASC";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
		    ResultSet resultSet = preparedStatement.executeQuery();
		    while (resultSet.next()) {
		    	Solution loadedSolution = new Solution();
		        loadedSolution.id = resultSet.getInt("id");
		        loadedSolution.created = resultSet.getString("created");
		        loadedSolution.updated = resultSet.getString("updated");
		        loadedSolution.description = resultSet.getString("description");
		        loadedSolution.exerciseId = resultSet.getInt("exercise_id");
		        loadedSolution.userId = resultSet.getInt("user_id");
		        solution.add(loadedSolution);
		        }
		    Solution[] uArray = new Solution[solution.size()];
		    uArray = solution.toArray(uArray);
		    return uArray;		
		}	
}

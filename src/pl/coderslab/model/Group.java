package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {
	
	private int id;
	private String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	
	static public Group[] loadAll(Connection conn) throws SQLException {
	    ArrayList<Group> groups = new ArrayList<Group>();
	    String sql = "SELECT * FROM Grups";
	    PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
	    	Group loadedGroup = new Group();
	        loadedGroup.id = resultSet.getInt("id");
	        loadedGroup.name = resultSet.getString("name");	        
	        groups.add(loadedGroup);}
	    Group[] uArray = new Group[groups.size()]; uArray = groups.toArray(uArray);
	    return uArray;}
	
	
	static public Group loadById(Connection conn, int id) throws SQLException {
	    String sql = "SELECT * FROM Group where id=?";
	    PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    preparedStatement.setInt(1, id);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (resultSet.next()) {
	    	Group loadedGroup = new Group();
	        loadedGroup.id = resultSet.getInt("id");
	        loadedGroup.name = resultSet.getString("name");
	        
	        return loadedGroup;}
	    return null;}
	
	public void delete(Connection conn) throws SQLException {
	    if (this.id != 0) {
	        String sql = "DELETE FROM Group WHERE id= ?";
	        PreparedStatement preparedStatement;
	        preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setInt(1, this.id);
	        preparedStatement.executeUpdate();
	        this.id=0;
	    }
	}
	
	public void saveToDB(Connection conn) throws SQLException {
		  if (this.id == 0) {
		    String sql = "INSERT INTO Group (name) VALUES (?)";
		    String generatedColumns[] = { "ID" };
		    PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql, generatedColumns);
		    preparedStatement.setString(1, this.name);
		    preparedStatement.executeUpdate();
		    ResultSet rs = preparedStatement.getGeneratedKeys();
		    if (rs.next()) {
		      this.id = rs.getInt(1);
		    }
		  }else {
			    String sql = "UPDATE Group SET name=?, where id = ?";
			    PreparedStatement preparedStatement;
			    preparedStatement = conn.prepareStatement(sql);
			    preparedStatement.setString(1, this.name);
			    preparedStatement.setInt(2, this.id);
			    preparedStatement.executeUpdate();
			}	
		}
	
	

}

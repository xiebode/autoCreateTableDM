package com.kedong.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class conn {
	
	public Connection connection = null;
	private  String driver = "dm.jdbc.driver.DmDriver";
	
	private  Statement statement = null;

	private  String url = "";
	private  String userName;
	private  String passWord;
	

	public void destoryConnection() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public conn(String URL,String usrname,String pwd) {
		this.url=URL;
		this.userName=usrname;
		this.passWord=pwd;
	}
	
	public ResultSet ExecuteQuery(String sql) {
		ResultSet rs=null;
		//创建statement对象
		try{
			rs = statement.executeQuery(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public boolean ExecuteSql(String Sql) throws SQLException{
		
		boolean bIsTrue = false;
		
		try {
			int i = statement.executeUpdate(Sql);
			if (i == 0){
				bIsTrue = true;
			}
		} catch (SQLException e) {
			 throw e;
		}
		
		
		return bIsTrue;
	}
	
	/**
	 * 初始化jdbc连接达蒙数据库
	 * author lizhennan
	 */
	public void initJdbcConnection() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, passWord);
			statement = connection.createStatement();
			System.out.println("connect success");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public  String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public  String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
			
}

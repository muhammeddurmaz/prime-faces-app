package com.journaldev.jsf.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {

	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;databaseName=testdata;user=muhammed;password=1234;integratedSecurity=false;encrypt=false;trustServerCertificate=true;");
			System.out.println("DATABASE CONNECTED");
			return con;
		} catch (Exception ex) {
			System.out.println("Database.getConnection() Error -->"
					+ ex.getMessage());
			return null;
		}
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}
}
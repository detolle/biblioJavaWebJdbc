package com.bibliotheque.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
//import com.mysql.cj.jdbc.MysqlDataSource;


public class DataSourceProvider {
	private static BasicDataSource singledatasource = null;

	public static BasicDataSource getdataSourceInstance() throws SQLException {
		if (singledatasource == null) {
//			Properties configProperties  = new Properties();
//			try {
//				configProperties.load(new FileInputStream("jdbc_biblio.properties"));
//			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			String nomPilote = configProperties.getProperty("driver");
//			String URLBD = configProperties.getProperty("url");
//			String authorizationID = configProperties.getProperty("user");
//			String password = configProperties.getProperty("pwd");			
System.out.println("jdbc_biblio.properties="+Messages.getString("url"));

			singledatasource = new BasicDataSource();
			singledatasource.setInitialSize(5);
			singledatasource.setUrl(Messages.getString("url"));
//        	datasource.setServerName("localhost");
//        	datasource.setPort(3306);
//        	datasource.setDatabaseName("tennis");
//        	datasource.setUseSSL(false);
//        	datasource.setServerTimezone("Europe/Paris");
			singledatasource.setUsername(Messages.getString("user"));
			singledatasource.setPassword(Messages.getString("pwd"));
		}
		// return singledatasource.getConnection();
		return singledatasource;
	}

}

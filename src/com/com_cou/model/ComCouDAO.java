package com.com_cou.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ComCouDAO implements ComCouDAO_interface{
	//DataSource
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDBg4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(ComCouVO com_couVO) {
	}
	
	@Override
	public void update(ComCouVO com_couVO) {
	}
	
	@Override
	public Set<ComCouVO> getComCousByCouid(String cou_id) {
		return null;
	}
	@Override
	public Set<ComCouVO> getComCousByMemid(Integer mem_id) {
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

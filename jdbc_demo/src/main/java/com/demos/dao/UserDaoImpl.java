package com.demos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demos.entity.User;
import com.demos.utils.DbUtil;

public class UserDaoImpl implements IUserDao {

	@Override
	public List<User> listUser() {
		List<User> userList = new ArrayList<User>();
		Connection conn = DbUtil.getConnection();
		String sql = "select * from user";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				
				user.setId(id);
				user.setName(name);
				user.setAge(age);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != ps && !ps.isClosed())
					ps.close();
				if(null != conn && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userList;
	}

	@Override
	public User getUser(Integer id) {
		Connection conn = DbUtil.getConnection();
		String sql = "select id,name,age from user where id=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				String name = rs.getString(2);
				int age = rs.getInt(3);
				
				user.setId(id);
				user.setName(name);
				user.setAge(age);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != ps && !ps.isClosed())
					ps.close();
				if(null != conn && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void saveUser(User user) {
		Connection conn = DbUtil.getConnection();
		String sql = "insert into user(id,name,age) values(?,?,?)";
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getName());
			ps.setInt(3, user.getAge());
			ps.execute();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != ps && !ps.isClosed())
					ps.close();
				if(null != conn && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateUser(User user) {
		Connection conn = DbUtil.getConnection();
		String sql = "update user set name=?,age=? where id=?";
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setInt(2, user.getAge());
			ps.setInt(3, user.getId());
			ps.execute();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != ps && !ps.isClosed())
					ps.close();
				if(null != conn && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteUser(Integer id) {
		Connection conn = DbUtil.getConnection();
		String sql = "delete from user where id=?";
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != ps && !ps.isClosed())
					ps.close();
				if(null != conn && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

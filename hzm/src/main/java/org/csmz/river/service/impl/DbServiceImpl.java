package org.csmz.river.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.csmz.river.bean.Book;
import org.csmz.river.mapper.BookDao;
import org.csmz.river.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;

public class DbServiceImpl implements DbService {

	private String msg;
	private DataSource ds;
	@Autowired
	private BookDao bookDao;
	

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public void showMsg() {
		System.out.println("This is "+msg);
	}

	@Override
	public int insert(Book book) {
		if(book == null){
			return 0;
		}
		if(book.getBookName() == null){
			book.setBookName("");
		}
		
		String sql = "insert into book (bookNo,bookName) values (?,?)";
		Connection con = null;
		
		try{
			con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, book.getBookNo());
			ps.setString(2, book.getBookName());
			
			int result = ps.executeUpdate();
			return result;

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return 0;
	}

	@Override
	public int delete(int id) {
		String sql = "delete from book where id = ? ";
		Connection con = null;
		
		try{
			con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			int result = ps.executeUpdate();
			return result;

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return 0;
	}

	@Override
	public int update(Book book) {
		if(book == null){
			return 0;
		}

		String sql = "update book set bookNo = ? , bookName = ? where id = ?";
		Connection con = null;
		
		try{
			con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, book.getBookNo());
			ps.setString(2, book.getBookName());
			ps.setInt(3, book.getId());
			
			int result = ps.executeUpdate();
			return result;

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return 0;
	}

	@Override
	public List<Book> getBook(int id) {
		if(id == 0){
			return bookDao.queryBooks();
		}else{
			List<Book> infoList = new ArrayList<>();
			Book result = bookDao.queryBook(id);
			if(result != null){
				infoList.add(result);
			}
			return infoList;
		}
	}

}

package org.csmz.river.mapper;

import java.util.List;

import org.csmz.river.bean.Book;

public interface BookDao {

public List<Book> queryBooks();
	
	public Book queryBook(int id);
	
	public int insertBook(Book book);
	
	public int deleteBook(int id);
	
	public int updateBook(Book book);
}

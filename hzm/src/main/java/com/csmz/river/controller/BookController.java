package com.csmz.river.controller;

import java.util.List;

import org.csmz.river.bean.Book;
import org.csmz.river.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Book")
public class BookController {

private DbService ds;
	
	@Autowired
	public void setDs(DbService ds) {
		this.ds = ds;
	}
	
	@RequestMapping
	public String showBooks(ModelMap model){
		
		List<Book> infoList = ds.getBook(0);
		model.addAttribute("bookList",infoList);
		
		return "showBook";
	}
	

	@RequestMapping("/insert")
	public String insertBook(){
		return "insertBook";
	}
	
	@RequestMapping("/insertData")
	public String insertBook(@RequestParam("bookNo") String bookNo,@RequestParam("bookName") String bookName,ModelMap model){
		//校验
		if("".equals(bookNo) || null == bookNo){
			model.addAttribute("msg","图书编号不能为空!");
			return "insertBook";
		}
		
		//数据保存
		Book info = new Book();
		info.setBookNo(bookNo);
		info.setBookName(bookName);
		int result = ds.insert(info);
		
		return "redirect:/Book";
	}
	
	@RequestMapping("/update")
	public String updateInfo(int id,ModelMap model){
		List<Book>  infoList = ds.getBook(id);
		if(infoList != null && infoList.size() > 0){
			model.addAttribute("book",infoList.get(0));
		}
		
		return "updateBook";
	}
	
	@RequestMapping("/updateData")
	public String updateInfo(Book info,ModelMap model){
		//校验
		if("".equals(info.getBookNo()) || null == info.getBookNo()){
			model.addAttribute("msg","图书编号不能为空!");
			return "updateBook";
		}
		
		//数据保存
		int result = ds.update(info);
		
		return "redirect:/Book";
	}
	
	@RequestMapping("/delete")
	public String deleteInfo(int id,ModelMap model){
		int result = ds.delete(id);
		
		return "redirect:/Book";
	}
	
}

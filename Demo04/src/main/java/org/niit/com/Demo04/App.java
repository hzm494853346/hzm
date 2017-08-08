package org.niit.com.Demo04;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.niit.com.bean.Info;
import org.niit.com.mapper.IInfo;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
			reader = Resources.getResourceAsReader("org/niit/com/config/config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public static void main( String[] args )
    {
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	IInfo infoService = sqlSession.getMapper(IInfo.class);
    	
//    	Info newInfo = new Info();
//    	newInfo.setName("stephen222");
//    	newInfo.setIntro("this is stephen222");
////    	int i = infoService.insertInfo(newInfo);
//    	
//    	newInfo.setId(5);
//    	int i = infoService.updateInfo(newInfo);
    	
    	int i = infoService.deleteInfo(5);
    	
    	System.out.println(i);
    	
    	
    	
    	List<Info> infoList = infoService.queryInfos();
    	for(Info info:infoList ){
    		System.out.println(info.getName());
    	}
    	
    	sqlSession.commit();
    	sqlSession.close();
    	
    	
    	
    }
}

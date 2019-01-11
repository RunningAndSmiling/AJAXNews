package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.News;
import util.JdbcUtil;

public class NewsJDBC {
	public List<News> getAllNews(){
		List<News> ls=new ArrayList<News>();
		JdbcUtil jdbcUtil = new JdbcUtil();
		Connection	con = jdbcUtil.getConnection();
		try{
			String sql="select * from t_news";
			PreparedStatement pst=con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				ls.add(new News(rs.getInt(1),rs.getString(2)));
			}

		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	}
	public int addNews(News news){
		JdbcUtil jdbcUtil = new JdbcUtil();
		Connection	con = jdbcUtil.getConnection();
		int row=0;
		try{
			String sql="insert into t_news(title) values(?)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, news.getTitle());
			row=pst.executeUpdate();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public int deleteNewsById(int id){
		JdbcUtil jdbcUtil = new JdbcUtil();
		Connection	con = jdbcUtil.getConnection();
		int row=0;
		try{
			String sql="delete from t_news where id=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, id);
			row=pst.executeUpdate();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public int updateNews(News news){
		JdbcUtil jdbcUtil = new JdbcUtil();
		Connection	con = jdbcUtil.getConnection();
		String sql="update t_news set title=? where id=?";
		int row=0;
		try{
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, news.getTitle());
			pst.setInt(2, news.getId());
			row=pst.executeUpdate();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	
	public News getNewsById(int id){
		News news=null;
		JdbcUtil jdbcUtil = new JdbcUtil();
		Connection	con = jdbcUtil.getConnection();
		try{
			String sql="select * from t_news where id=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				news=new News(rs.getInt(1),rs.getString(2));
			}

		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return news;
	}
	public int getTotalRecords() {
		JdbcUtil jdbcUtil = new JdbcUtil();
		Connection	con = jdbcUtil.getConnection();
		int totalRecords=0;
		String sql ="SELECT count(*) from t_news";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				totalRecords=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalRecords;
	}
	public List<News> getPageNews(int pageNum,int pageSize) {
		List<News> ls=new ArrayList<News>();
 		JdbcUtil jdbcUtil = new JdbcUtil();
		Connection	con = jdbcUtil.getConnection();
		String sql ="SELECT * FROM  t_news limit ?,?";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, (pageNum-1)*pageSize);
			pst.setInt(2, pageSize);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String title = rs.getString("title");
				News news=new News(id,title);
				ls.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	}
	
}

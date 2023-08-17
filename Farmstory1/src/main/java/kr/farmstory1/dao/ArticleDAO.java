package kr.farmstory1.dao;

import java.util.ArrayList;
import java.util.List;

import kr.farmstory1.db.DBHelper;
import kr.farmstory1.db.SQL;
import kr.farmstory1.dto.ArticleDTO;

public class ArticleDAO extends DBHelper{
	
	public void insertArticle(ArticleDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getCate());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getWriter());
			psmt.setString(5, dto.getRegip());
			
			psmt.executeUpdate();
			
			close();

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public List<ArticleDTO> selectArticles(String cate, int start) {
		ArticleDTO dto = null;
		List<ArticleDTO> articles = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
			psmt.setString(1, cate);
			psmt.setInt(2, start);
			rs = psmt.executeQuery();
			while(rs.next()) {
				dto = new ArticleDTO();
				dto.setNo(rs.getString(1));
				dto.setNo(rs.getString(2));
				dto.setNo(rs.getString(3));
				dto.setNo(rs.getString(4));
				dto.setNo(rs.getString(5));
				dto.setNo(rs.getString(6));
				dto.setNo(rs.getString(7));
				dto.setNo(rs.getString(8));
				dto.setNo(rs.getString(9));
				dto.setNo(rs.getString(10));
				dto.setNo(rs.getString(11));
				
				articles.add(dto);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articles;
	}
}	

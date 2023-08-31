package kr.co.Jboard2.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.db.DBHelper;
import kr.co.Jboard2.db.SQL;
import kr.co.Jboard2.dto.ArticleDTO;

public class ArticleDAO extends DBHelper{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertArticle(ArticleDTO dto) {
		int no =0;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			psmt.executeUpdate();
			rs = stmt.executeQuery(SQL.SELECT_ARTICLE_MAX_NO);
			if(rs.next()) {
				no = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("insertArticle() error : "+e.getMessage());
		}
		return no;
	}
	public ArticleDTO selectArticle(String no) {
		return null;
	}
	public List<ArticleDTO> selectArticles() {
		return null;
	}
	public void updateArticle(ArticleDTO dto) {
		
	}
	public void deleteArticle(String no) {
		
	}
}

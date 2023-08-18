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
	
	public ArticleDTO selectArticle(String no) {
		ArticleDTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new ArticleDTO();
				dto.setNo(rs.getInt("no"));
				dto.setParent(rs.getInt("parent"));
				dto.setComment(rs.getInt("comment"));
				dto.setCate(rs.getString("cate"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getInt("file"));
				dto.setHit(rs.getInt("hit"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));	
			}
			close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
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
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setComment(rs.getInt(3));
				dto.setCate(rs.getString(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setFile(rs.getInt(7));
				dto.setHit(rs.getInt(8));
				dto.setWriter(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdate(rs.getString(11));
				dto.setNick(rs.getString(12));
				
				articles.add(dto);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articles;
	}
	
	public int selectCountTotal(String cate) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
			psmt.setString(1, cate);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}	

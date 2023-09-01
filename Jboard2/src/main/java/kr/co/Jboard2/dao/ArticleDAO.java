package kr.co.Jboard2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.db.DBHelper;
import kr.co.Jboard2.db.SQL;
import kr.co.Jboard2.dto.ArticleDTO;
import kr.co.Jboard2.dto.FileDTO;

public class ArticleDAO extends DBHelper{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertArticle(ArticleDTO dto) {
		int no =0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false); // 트랜잭션 시작
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getFile());
			psmt.setString(4, dto.getWriter());
			psmt.setString(5, dto.getRegip());
			psmt.executeUpdate();
			rs = stmt.executeQuery(SQL.SELECT_ARTICLE_MAX_NO);
			conn.commit(); // 트랜잭션 처리
			if(rs.next()) {
				no = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("insertArticle() error : "+e.getMessage());
		}
		return no;
	}
	public int insertComment(ArticleDTO dto) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
			psmt.setInt(1, dto.getParent());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			result = psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("insertArticle() error : "+e.getMessage());
		}
		return result;
	}
	
	public ArticleDTO selectArticle(String no) {
		ArticleDTO dto = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			PreparedStatement psmt2 = conn.prepareStatement(SQL.UPDATE_HIT_COUNT);
			psmt.setString(1, no);
			psmt2.setString(1, no);
			rs = psmt.executeQuery();
			psmt2.executeUpdate();
			conn.commit();
			if(rs.next()) {
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
				// 파일정보
				FileDTO fileDTO = new FileDTO();
				fileDTO.setFno(rs.getInt(12));
				fileDTO.setAno(rs.getInt(13));
				fileDTO.setOriName(rs.getString(14));
				fileDTO.setNewName(rs.getString(15));
				fileDTO.setDownload(rs.getInt(16));
				fileDTO.setRdate(rs.getString(17));
				
				// dto를 return하기 때문에 dto에 FileDTO 객체를 넣어줘야 함
				dto.setFileDTO(fileDTO);
			}
			psmt2.close();
			close();
		} catch (Exception e) {
			logger.error("selectArticle() error : "+e.getMessage());
		}
		return dto;
	}

	public int selectCountTotal(String search) {
		int total = 0;
		try {
			conn = getConnection();
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL_FOR_SEARCH);
				psmt.setString(1, "%"+search+"%");
			}
			rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCountTotal() error : "+e.getMessage());
		}
		return total;
	}
	
	public List<ArticleDTO> selectArticles(int start, String search) {
		List<ArticleDTO> articles = new ArrayList<>();
		try {
			conn = getConnection();
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
				psmt.setInt(1, start);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES_FOR_SEARCH);
				psmt.setString(1, "%"+search+"%");
				psmt.setInt(2, start);
			}
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
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
			logger.error("selectArticles() error : "+e.getMessage());
		}
		return articles;
	}
	
	public List<ArticleDTO> selectComments(String no) {
		List<ArticleDTO> comments = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COMMENTS);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
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
				comments.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectComments() error : "+e.getMessage());
		}
		return comments;
	}
	public void updateArticle(ArticleDTO dto) {
		
	}
	
	public void updateArticleForCommentPlus(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT_PLUS);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateArticleForCommentMinus(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT_MINUS);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			logger.error("updateArticleForCommentMinus"+e.getMessage());
		}
	}
	
	public void deleteArticle(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("deleteArticle() error : "+e.getMessage());
		}
		
	}
	public int deleteComment(String no) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_COMMENT);
			psmt.setString(1, no);
			result = psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("deleteComment() error : "+e.getMessage());
		}
		return result;
	}
}

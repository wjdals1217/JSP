package kr.co.farmstory2.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;

public class ArticleDAO extends DBHelper{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertArticle(ArticleDTO dto) {
		int no = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getCate());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setInt(4, dto.getFile());
			psmt.setString(5, dto.getWriter());
			psmt.setString(6, dto.getRegip());
			psmt.executeUpdate();
			rs = stmt.executeQuery(SQL.SELECT_ARTICLE_MAX_NO);
			conn.commit();
			if(rs.next()) {
				no = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.debug("insertArticle() : "+e.getMessage());
		}
		return no;
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
	public int selectCountTotal(String cate, String search) {
		int total = 0;
		try {
			conn = getConnection();
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
				psmt.setString(1, cate);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL_FOR_SEARCH);
				psmt.setString(1, cate);
				psmt.setString(2, "%"+search+"%");
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
	
	public List<ArticleDTO> selectArticles(String cate, String search, int start) {
		List<ArticleDTO> articles = new ArrayList<>();
		try {
			conn = getConnection();
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
				psmt.setString(1, cate);
				psmt.setInt(2, start);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES_FOR_SEARCH);
				psmt.setString(1, cate);
				psmt.setString(2, "%"+search+"%");
				psmt.setInt(3, start);
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
	public void updateArticle(ArticleDTO dto) {}
	public void deleteArticle(String no) {}

}
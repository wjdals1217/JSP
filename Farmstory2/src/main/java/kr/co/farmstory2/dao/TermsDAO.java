package kr.co.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.famrstory2.db.DBHelper;
import kr.co.famrstory2.db.SQL;
import kr.co.famrstory2.dto.TermsDTO;

public class TermsDAO extends DBHelper{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public void insertTerms(TermsDTO dto){}
	public TermsDTO selectTerm(String terms){
		return null;
	}
	public TermsDTO selectTerms(){
		TermsDTO terms = new TermsDTO();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_TERMS);
			if(rs.next()) {
				terms = new TermsDTO();
				terms.setTerms(rs.getString(1));
				terms.setPrivacy(rs.getString(2));
			}
			close();
		} catch (Exception e) {
			logger.error("selectTerms() "+e.getMessage());
		}
		
		return terms;
	}
	public void updateTerms(TermsDTO dto){}
	public void deleteTerms(String terms){}
}

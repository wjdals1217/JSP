package kr.co.Jboard2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.db.DBHelper;
import kr.co.Jboard2.db.SQL;
import kr.co.Jboard2.dto.TermsDTO;

public class TermsDAO extends DBHelper{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertTerms() {}
	public TermsDTO selectTerms() {
		TermsDTO dto = new TermsDTO();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_TERMS);
			if(rs.next()) {
				dto.setTerms(rs.getString(1));
				dto.setPrivacy(rs.getString(2));
			}
			close();
		} catch (Exception e) {
			logger.error("selectTerms error : "+e.getMessage());
		}
		return dto;
	}
	public void updateTerms() {}
	public void deleteTerms() {}
}

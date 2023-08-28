package kr.co.Jboard2.service;

import kr.co.Jboard2.dao.TermsDAO;
import kr.co.Jboard2.db.SQL;
import kr.co.Jboard2.dto.TermsDTO;

public class TermsService {
	
	TermsDAO dao = new TermsDAO();
	
	public void insertTerms() {}
	public TermsDTO selectTerms() {
		return dao.selectTerms();
	}
	public void updateTerms() {}
	public void deleteTerms() {}

}

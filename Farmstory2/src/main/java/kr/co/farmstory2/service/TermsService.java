package kr.co.farmstory2.service;

import java.util.List;

import kr.co.farmstory2.dao.TermsDAO;
import kr.co.farmstory2.dto.TermsDTO;

public class TermsService {
	
	private TermsDAO dao = new TermsDAO();
	
	public void insertTerms(TermsDTO dto){
		dao.insertTerms(dto);
	}
	public TermsDTO selectTerm(String terms){
		return dao.selectTerm(terms);
	}
	public TermsDTO selectTerms(){
		return dao.selectTerms();
	}
	public void updateTerms(TermsDTO dto){
		dao.updateTerms(dto);
	}
	public void deleteTerms(String terms){
		dao.deleteTerms(terms);
	}
}

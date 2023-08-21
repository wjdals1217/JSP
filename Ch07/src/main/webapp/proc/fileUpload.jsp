<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.io.File"%>
<%@page import="java.util.UUID"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String path = application.getRealPath("/upload"); // 시스템 경로(절대경로 사용)
	int maxSize = 1024 * 1024 * 10; // 허용할 최대 파일 크기
	
	// 파일 폼 데이터 수신
	// MultipartRequest에서 파일 스트림 작업
	MultipartRequest mr = new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	String uid = mr.getParameter("uid");
	String name = mr.getParameter("name");
	String fname = mr.getOriginalFileName("fname");
	
	// 파일명 수정
	int i = fname.lastIndexOf(".");
	String ext = fname.substring(i); // 확장자
	
	String uuid = UUID.randomUUID().toString();
	String sName = uuid+ext; // 중복이 없는 이름의 파일명 생성
	
	File f1 = new File(path+ "/"+fname);
	File f2 = new File(path+ "/"+sName);
	
	f1.renameTo(f2); 	// 원본 파일 저장 후 중복 없는 파일 이름 수정
	
	// 원본 파일명 INSERT
	Context ctx = (Context) new InitialContext().lookup("java:comp/env");
	DataSource ds = (DataSource) ctx.lookup("jdbc/Jboard");
	
	try{
		Connection conn = ds.getConnection();
		PreparedStatement psmt = conn.prepareStatement("INSERT INTO `FileTest` SET `oName`=?, `sName`=?, `rdate`=NOW()");
		psmt.setString(1, fname);
		psmt.setString(2, sName);
		psmt.executeUpdate();
		psmt.close();
		conn.close();
	}catch(Exception e){
		e.printStackTrace();		
	}
	
	response.sendRedirect("../2_FileDownload.jsp");
	
%>
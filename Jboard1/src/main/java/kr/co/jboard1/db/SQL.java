package kr.co.jboard1.db;

public class SQL {

	// user
	public static final String INSERT_USER ="INSERT INTO `user` "
																	+ "SET `uid`=?, "
																	+ "`pass`=SHA2(?, 256), "
																	+ "`name`=?, "
																	+ "`nick`=?, "
																	+ "`email`=?, "
																	+ "`hp`=?, "
																	+ "`zip`=?, "
																	+ "`addr1`=?, "
																	+ "`addr2`=?, "
																	+ "`regip`=?, "
																	+ "`regDate`=NOW()";
	
	
	public static final String SELECT_USER ="SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?, 256)";

	public static final String SELECT_COUNT_UID ="SELECT COUNT(*) FROM `User` WHERE `uid`=?";
	public static final String SELECT_COUNT_NICK ="SELECT COUNT(*) FROM `User` WHERE  `nick`=?";
	public static final String SELECT_COUNT_EMAIL ="SELECT COUNT(*) FROM `User` WHERE `email`=?";
	public static final String SELECT_COUNT_HP ="SELECT COUNT(*) FROM `User` WHERE `hp`=?";
	public static final String SELECT_TERMS = "SELECT * FROM `terms`";
	
	// Article
	public static final String	INSERT_ARTICLE = "INSERT INTO `Article` SET "
																			+ "`title`=?, "
																			+ "`content`= ?, "
																			+ "`writer`=?, "
																			+ "`regip`=?, "
																			+	"`rdate`=NOW()";
	
	public static final String SELECT_ARTICLES = "SELECT `no`, `title`, `comment`, `writer`, `nick`, `rdate`, `hit` FROM `Article` AS A JOIN `User` AS B ON A.writer = B.uid ORDER BY A.rdate DESC;";
	
}

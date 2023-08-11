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

	public static final String	INSERT_COMMENT = "INSERT INTO `Article` SET "
																			+ "`parent`= ?, "
																			+ "`content`= ?, "
																			+ "`writer`=?, "
																			+ "`regip`=?, "
																			+	"`rdate`=NOW()";
	
	public static final String SELECT_ARTICLE = "SELECT * FROM `Article` WHERE `no`=?";
	
	public static final String SELECT_ARTICLES = "SELECT A.*, B.`nick` FROM `Article` AS A "
																		+ "JOIN `User` AS B ON A.writer = B.uid "
																		+ "WHERE `parent`=0 "
																		+ "ORDER BY A.`no` DESC "
																		+ "LIMIT ?, 10";
	
	public static final String SELECT_COMMENTS = "SELECT A.*, B.`nick` FROM `Article` AS A "
																			+ "JOIN `User` AS B ON A.writer = B.uid "
																			+ "WHERE `parent`=?";
	
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `Article` WHERE `parent`= 0;";
	
	public static final String UPDATE_ARTICLE_FOR_COMMENT_PLUS = "UPDATE `Article` SET `comment` = `comment` + 1 WHERE `no`=?";

	public static final String UPDATE_ARTICLE_FOR_COMMENT_MINUS = "UPDATE `Article` SET `comment` = `comment` - 1 WHERE `no`=?";
	
	public static final String UPDATE_COMMENT = "UPDATE `Article` SET `content`=? WHERE `no`=?";
	
	public static final String UPDATE_ARTICLE = "UPDATE `Article` SET `title`=?, `content`=? WHERE `no`=?";
	
	public static final String DELETE_ARTICLE = "DELETE FROM `Article`WHERE `no`=? OR `parent`=?";
	
	public final static String DELETE_COMMENT = "DELETE FROM `Article` WHERE `no`=?";
	
	
}

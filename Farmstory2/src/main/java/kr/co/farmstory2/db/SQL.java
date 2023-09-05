package kr.co.farmstory2.db;

public class SQL {

	// Terms
	public static final String SELECT_TERMS = "SELECT * FROM `Terms`";
	
	// User
	public static final String INSERT_USER = "INSERT INTO `User` SET "
																	+ "`uid`=?, "
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
	
	public static final String SELECT_USER = "SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?, 256)";

	public static final String SELECT_COUNT_UID = "SELECT COUNT(*) FROM `User` WHERE `uid`=?";

	public static final String SELECT_COUNT_NICK = "SELECT COUNT(*) FROM `User` WHERE `nick`=?";
	
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) FROM `User` WHERE `email`=?";

	public static final String SELECT_COUNT_HP = "SELECT COUNT(*) FROM `User` WHERE `hp`=?";
	
	// Article
	public static final String	INSERT_ARTICLE = "INSERT INTO `Article` SET "
																			+ "`cate`=?, "
																			+ "`title`=?, "
																			+ "`content`= ?, "
																			+ "`file`= ?, "
																			+ "`writer`=?, "
																			+ "`regip`=?, "
																			+	"`rdate`=NOW()";
	
	public static final String SELECT_ARTICLE_MAX_NO = "SELECT MAX(`no`) FROM `Article`";
	
	// File
	public static final String	INSERT_FILE = "INSERT INTO `File` SET "
																	+ "`ano`=?, "
																	+ "`oriName`= ?, "
																	+ "`newName`=?, "
																	+	"`rdate`=NOW()";
}
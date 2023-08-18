package kr.farmstory1.db;

public class SQL {

	public static final String SELECT_TERMS = "SELECT * FROM `Terms`";
	public static final String SELECT_USER = "SELECT * FROM `User` WHERE `uid`=? AND `pass` = SHA2(?, 256)";

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
	
	public static final String INSERT_ARTICLE = "INSERT INTO `Article`SET "
																		+ "`cate`=?, "
																		+ "`title`=?, "
																		+ "`content`=?, "
																		+ "`writer`=?, "
																		+ "`regip`=?, "
																		+ "`rDate`=NOW()";
	public static final String SELECT_ARTICLE = "SELECT * FROM `Article` WHERE `no`=?";
	
	// 카테고리별로 글을 불러와야하므로 cate를 인덱스로 넣어주는 것임
	public static final String SELECT_ARTICLES = "SELECT a.*, b.`nick` FROM `Article` AS a "
																			+ "JOIN `User` AS b ON a.`writer`=b.`uid` "
																			+ "WHERE `parent`=0 AND `cate`=? "
																			+ "ORDER BY `no` DESC "
																			+ "LIMIT ?, 10";
	// 카테고리별 글 수를 세어줘야 게시판당 관련된 글의 개수를 찾을 수 있다.
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0 AND `cate`=?";
}

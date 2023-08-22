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
	
	public static final String INSERT_PRODUCT = "INSERT INTO `Product`SET "
																			+ "`type`=?, "
																			+ "`pName`=?, "
																			+ "`price`=?, "
																			+ "`delivery`=?, "
																			+ "`stock`=?, "
																			+ "`thumb1`=?, "
																			+ "`thumb2`=?, "
																			+ "`thumb3`=?, "
																			+ "`seller`=?, "
																			+ "`etc`=?, "
																			+ "`rDate`=NOW()";
	
	public static final String INSERT_COMMENT = "INSERT INTO `Article`SET "
																			+ "`parent`=?, "
																			+ "`cate`=?, "
																			+ "`content`=?, "
																			+ "`writer`=?, "
																			+ "`regip`=?, "
																			+ "`rDate`=NOW()";
	public static final String UPDATE_COMMENT_COUNT_PLUS ="UPDATE `Article` SET `comment`= `comment` + 1 WHERE `no`=?";
	
	public static final String UPDATE_COMMENT_COUNT_MINUS ="UPDATE `Article` SET `comment`= `comment` - 1 WHERE `no`=?";
	
	public static final String SELECT_ARTICLE = "SELECT * FROM `Article` WHERE `no`=?";

	public static final String SELECT_LATESTS = "SELECT `no`, `title`, rdate FROM `Article` "
																			+ "WHERE `parent`=0 AND `cate`=? Order BY `no` DESC LIMIT ?";
	
	
	// 카테고리별로 글을 불러와야하므로 cate를 인덱스로 넣어주는 것임
	public static final String SELECT_ARTICLES = "SELECT a.*, b.`nick` FROM `Article` AS a "
																			+ "JOIN `User` AS b ON a.`writer`=b.`uid` "
																			+ "WHERE `parent`=0 AND `cate`=? "
																			+ "ORDER BY `no` DESC "
																			+ "LIMIT ?, 10";
	
	public static final String SELECT_COMMENTS = "SELECT a.*, b.`nick` FROM `Article` AS a "
																				+ "JOIN `User` AS b ON a.`writer`=b.`uid` "
																				+ "WHERE `parent`=?";
	
	public static final String SELECT_PRODUCTS_ALL = "SELECT * FROM `Product` WHERE `stock` > 0 LIMIT ?, 10";

	public static final String SELECT_PRODUCTS_TYPE = "SELECT * FROM `Product` WHERE `stock` > 0 AND `type`=? LIMIT ?, 10";
	
	// 카테고리별 글 수를 세어줘야 게시판당 관련된 글의 개수를 찾을 수 있다.
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0 AND `cate`=?";

	public static final String SELECT_COUNT_PRODUCTS_ALL = "SELECT COUNT(*) FROM `Product`WHERE `stock` > 0";

	public static final String SELECT_COUNT_PRODUCTS_TYPE = "SELECT COUNT(*) FROM `Product`WHERE `stock` > 0 AND `type`=?";

	public static final String UPDATE_ARTICLE = "UPDATE `Article` SET `title`=?, `content`=? 	WHERE `no`=?";

	public static final String UPDATE_COMMENT = "UPDATE `Article` SET `content`=?  WHERE `no`=?";

	
	public static final String DELETE_ARTICLE = "DELETE FROM `Article`	WHERE `no`=? OR `parent`=?";

	public static final String DELETE_COMMENT = "DELETE FROM `Article`	WHERE `no`=?";


	


}

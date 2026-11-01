package DBPKG;

import java.sql.*;
import java.util.*;

public class BoardDAO {
	
private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	private BoardDAO() {}
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 전체 게시글 조회
	public List<BoardDTO> getBoardList() {
		
		BoardDTO board = null;
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		String str = "select p_no, img, p_name, category from board";
		
		try {
			
			con = DBConn.getConnection();
			pstmt = con.prepareStatement(str);
			rs = pstmt.executeQuery();
			
			while(rs.next() ) {
				board = new BoardDTO();
				board.setP_no(rs.getString(1));
				board.setImg(rs.getString(2));
				board.setP_name(rs.getString(3));
				board.setCategory(rs.getInt(4));
				boardList.add(board);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, pstmt, rs);
		}
		return boardList;
	}
	
	// 페이징 전체 게시글 조회
	public List<BoardDTO> getBoardListPaging(int startRow, int pageSize) {

	    BoardDTO board = null;
	    List<BoardDTO> boardList = new ArrayList<>();

	    String sql = "select p_no, img, p_name, category "
	               + "from board "
	               + "order by p_no DESC "
	               + "offset ? rows fetch next ? rows only";

	    try {

	        con = DBConn.getConnection();
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, startRow);
	        pstmt.setInt(2, pageSize);

	        rs = pstmt.executeQuery();

	        while(rs.next()) {
	            board = new BoardDTO();
	            board.setP_no(rs.getString(1));
	            board.setImg(rs.getString(2));
	            board.setP_name(rs.getString(3));
	            board.setCategory(rs.getInt(4));
	            boardList.add(board);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(con, pstmt, rs);
	    }

	    return boardList;
	}

	// 전체 게시글 수
	public int getBoardCount() {

	    int count = 0;
	    String sql = "select count(*) from board";

	    try {
	        con = DBConn.getConnection();
	        pstmt = con.prepareStatement(sql);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            count = rs.getInt(1);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(con, pstmt, rs);
	    }

	    return count;
	}

	
	// travel 조회
	public List<BoardDTO> getTravelList() {
		
		BoardDTO board = null;
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		String str = "select p_no, img, p_name, category from board where category = 0";
		
		try {
			con = DBConn.getConnection();
			pstmt = con.prepareStatement(str);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new BoardDTO();
				board.setP_no(rs.getString(1));
				board.setImg(rs.getString(2));
				board.setP_name(rs.getString(3));
				board.setCategory(rs.getInt(4));
				boardList.add(board);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, pstmt, rs);
		}
		return boardList;
		
	}
	
	// 페이징 travel 게시글 조회
	public List<BoardDTO> getTravelListPaging(int startRow, int pageSize) {

	    BoardDTO board = null;
	    List<BoardDTO> boardList = new ArrayList<>();

	    String sql = "select p_no, img, p_name, category "
	               + "from board "
	               + "where category = 0"
	               + "order by p_no DESC "
	               + "offset ? rows fetch next ? rows only";

	    try {

	        con = DBConn.getConnection();
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, startRow);
	        pstmt.setInt(2, pageSize);

	        rs = pstmt.executeQuery();

	        while(rs.next()) {
	            board = new BoardDTO();
	            board.setP_no(rs.getString(1));
	            board.setImg(rs.getString(2));
	            board.setP_name(rs.getString(3));
	            board.setCategory(rs.getInt(4));
	            boardList.add(board);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(con, pstmt, rs);
	    }

	    return boardList;
	}

	// 전체 게시글 수
	public int getTravelCount() {

	    int count = 0;
	    String sql = "select count(*) from board where category = 0";

	    try {
	        con = DBConn.getConnection();
	        pstmt = con.prepareStatement(sql);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            count = rs.getInt(1);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(con, pstmt, rs);
	    }

	    return count;
	}
	
	// snap 조회
	public List<BoardDTO> getSnapList() {
		
		BoardDTO board = null;
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		String str = "select p_no, img, p_name, category from board where category = 1";
		
		try {
			con = DBConn.getConnection();
			pstmt = con.prepareStatement(str);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new BoardDTO();
				board.setP_no(rs.getString(1));
				board.setImg(rs.getString(2));
				board.setP_name(rs.getString(3));
				board.setCategory(rs.getInt(4));
				boardList.add(board);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, pstmt, rs);
		}
		return boardList;
		
	}
	
	// 페이징 travel 게시글 조회
	public List<BoardDTO> getSnapListPaging(int startRow, int pageSize) {

	    BoardDTO board = null;
	    List<BoardDTO> boardList = new ArrayList<>();

	    String sql = "select p_no, img, p_name, category "
	               + "from board "
	               + "where category = 1"
	               + "order by p_no DESC "
	               + "offset ? rows fetch next ? rows only";

	    try {

	        con = DBConn.getConnection();
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, startRow);
	        pstmt.setInt(2, pageSize);

	        rs = pstmt.executeQuery();

	        while(rs.next()) {
	            board = new BoardDTO();
	            board.setP_no(rs.getString(1));
	            board.setImg(rs.getString(2));
	            board.setP_name(rs.getString(3));
	            board.setCategory(rs.getInt(4));
	            boardList.add(board);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(con, pstmt, rs);
	    }

	    return boardList;
	}

	// 전체 게시글 수
	public int getSnapCount() {

	    int count = 0;
	    String sql = "select count(*) from board where category = 1";

	    try {
	        con = DBConn.getConnection();
	        pstmt = con.prepareStatement(sql);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            count = rs.getInt(1);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(con, pstmt, rs);
	    }

	    return count;
	}
	
	// daily 조회
	public List<BoardDTO> getDailyList() {
		
		BoardDTO board = null;
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		String str = "select p_no, img, p_name, category from board where category = 2";
		
		try {
			con = DBConn.getConnection();
			pstmt = con.prepareStatement(str);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new BoardDTO();
				board.setP_no(rs.getString(1));
				board.setImg(rs.getString(2));
				board.setP_name(rs.getString(3));
				board.setCategory(rs.getInt(4));
				boardList.add(board);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, pstmt, rs);
		}
		return boardList;
		
	}
	
	// 페이징 travel 게시글 조회
	public List<BoardDTO> getDailyListPaging(int startRow, int pageSize) {

	    BoardDTO board = null;
	    List<BoardDTO> boardList = new ArrayList<>();

	    String sql = "select p_no, img, p_name, category "
	               + "from board "
	               + "where category = 2"
	               + "order by p_no DESC "
	               + "offset ? rows fetch next ? rows only";

	    try {

	        con = DBConn.getConnection();
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, startRow);
	        pstmt.setInt(2, pageSize);

	        rs = pstmt.executeQuery();

	        while(rs.next()) {
	            board = new BoardDTO();
	            board.setP_no(rs.getString(1));
	            board.setImg(rs.getString(2));
	            board.setP_name(rs.getString(3));
	            board.setCategory(rs.getInt(4));
	            boardList.add(board);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(con, pstmt, rs);
	    }

	    return boardList;
	}

	// 전체 게시글 수
	public int getDailyCount() {

	    int count = 0;
	    String sql = "select count(*) from board where category = 2";

	    try {
	        con = DBConn.getConnection();
	        pstmt = con.prepareStatement(sql);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            count = rs.getInt(1);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(con, pstmt, rs);
	    }

	    return count;
	}
	
	// 게시글 1건 조회
	public BoardDTO getBoard(String p_no) {
		BoardDTO board = new BoardDTO();
		String sql = "select * from board where p_no = ?";
		
		try {
			con = DBConn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, p_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board.setP_no(rs.getString("p_no"));
				board.setImg(rs.getString("img"));
				board.setP_name(rs.getString("p_name"));
				board.setP_content(rs.getString("p_content"));
				board.setCategory(rs.getInt("category"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, pstmt, rs);
		}
		return board;
	}
	
	// 게시글 생성
	public int insertBoard(BoardDTO board) {
		int cnt = 0;
		String sql = "insert into board valuse(?,?,?,?,?)";
		
		try {
			
			con = DBConn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getP_no());
			pstmt.setString(2, board.getImg());
			pstmt.setString(3, board.getP_name());
			pstmt.setString(4, board.getP_content());
			pstmt.setInt(5, board.getCategory());
			cnt = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, pstmt, rs);
		}
		return cnt;
	}
	
	// 게시글 수정
	public int updateBoard(BoardDTO board) {
		int cnt = 0;
		String sql = "update board set "
				+ "p_no=?, img=?, p_name=?, p_content=?, category=?";
		
		try {
			con = DBConn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getP_no());
			pstmt.setString(2, board.getImg());
			pstmt.setString(3, board.getP_name());
			pstmt.setString(4, board.getP_content());
			pstmt.setInt(5, board.getCategory());
			cnt = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, pstmt, rs);
		}
		return cnt;
	}
	
	// 게시글 삭제
	public int deleteBoard(String p_no) {
		int cnt = 0;
		String sql = "delete board where p_no = ?";
		
		try {
			con = DBConn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, p_no);
			rs = pstmt.executeQuery();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, pstmt, rs);
		}
		return cnt;
	}
}

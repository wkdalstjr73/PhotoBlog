package DBPKG;

import java.sql.*;
import java.util.*;

public class BoardDAO {
	
// BoardDAO 싱글톤 객체 생성
private static BoardDAO instance = new BoardDAO();
	//BoardDAO 인스턴스 반환
	public static BoardDAO getInstance() {
		return instance;
	}
	
	// 외부에서 객체를 직접 생성하지 못하도록 생성자 제한
	private BoardDAO() {}
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 전체 게시글 목록 조회
    // 게시글 번호, 이미지 파일명, 제목, 카테고리 정보를 가져와 List로 반환
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
	
	// 전체 게시글 목록 페이징 조회
    // 시작 위치(startRow)와 페이지당 게시글 수(pageSize)를 기준으로 조회
    // 게시글 번호를 기준으로 최신순 정렬
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

	// 전체 게시글 수 조회
    // 전체 게시글의 개수를 반환하여 페이지 수 계산에 사용
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
	
	// Travel 카테고리 게시글 페이징 조회
    // category 값이 0인 게시글을 최신순으로 정렬하여 페이지 단위로 조회
	public List<BoardDTO> getTravelListPaging(int startRow, int pageSize) {

	    BoardDTO board = null;
	    List<BoardDTO> boardList = new ArrayList<>();

	    String sql = "select p_no, img, p_name, category "
	               + "from board "
	               + "where category = 0 "
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

	// Travel 카테고리 게시글 수 조회
    // 해당 카테고리의 전체 게시글 개수를 반환
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
	
	// Snap 카테고리 게시글 목록 조회
    // category 값이 1인 게시글만 조회
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
	
	// Snap 카테고리 게시글 페이징 조회
    // category 값이 1인 게시글을 최신순으로 정렬하여 페이지 단위로 조회
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

	// Snap 카테고리 게시글 수 조회
    // 해당 카테고리의 전체 게시글 개수를 반환
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
	
	// Daily 카테고리 게시글 목록 조회
    // category 값이 2인 게시글만 조회
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
	
	// Daily 카테고리 게시글 페이징 조회
    // category 값이 2인 게시글을 최신순으로 정렬하여 페이지 단위로 조회
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

	// Daily 카테고리 게시글 수 조회
    // 해당 카테고리의 전체 게시글 개수를 반환
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
	
	// 게시글 상세 조회
    // 게시글 번호(p_no)를 기준으로 특정 게시글의 전체 정보 조회
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
	
	// 게시글 등록
    // BoardDTO에 담긴 게시글 정보를 BOARD 테이블에 저장
    // 등록 성공 시 처리된 행의 개수 반환
	public int insertBoard(BoardDTO board) {
		int cnt = 0;
		String sql = "update board set "
		        + "img=?, p_name=?, p_content=?, category=? "
		        + "where p_no=?";
		
		try {
			
			con = DBConn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getImg());
			pstmt.setString(2, board.getP_name());
			pstmt.setString(3, board.getP_content());
			pstmt.setInt(4, board.getCategory());
			pstmt.setString(5, board.getP_no());
			cnt = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, pstmt, rs);
		}
		return cnt;
	}
	
	// 게시글 수정
    // BoardDTO에 담긴 게시글 정보를 기준으로 기존 게시글 정보 수정
    // 수정 성공 시 처리된 행의 개수 반환
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
	// 게시글 번호(p_no)를 기준으로 특정 게시글 삭제
	public int deleteBoard(String p_no) {
	    int cnt = 0;
	    String sql = "delete from board where p_no = ?";

	    try {
	        con = DBConn.getConnection();
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, p_no);
	        cnt = pstmt.executeUpdate();  // 수정된 부분

	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(con, pstmt, rs);
	    }
	    return cnt;
	}
}

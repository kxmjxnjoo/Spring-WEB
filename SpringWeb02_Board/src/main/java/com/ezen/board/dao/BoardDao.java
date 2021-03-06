package com.ezen.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.board.dto.BoardDto;
import com.ezen.board.dto.Paging;
import com.ezen.board.dto.ReplyVO;
import com.ezen.board.util.DataBaseManager;

@Repository
public class BoardDao {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Autowired
	DataBaseManager dbm;
	
	
	public int getAllCount() {
		int count=0;
		String sql = "select count(*) as count from board";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt("count");
		} catch (SQLException e) {	e.printStackTrace();
		} finally { dbm.close(con, pstmt, rs); }
		return count;
	}
	
	
	
	public ArrayList<BoardDto> getBoardsMain( Paging paging ) {
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		con = dbm.getConnection();
		// String sql = "select * from board order by num desc";
		String sql = "select * from ("
				+ " select * from ("
				+ " select rownum as rn, b.* from ((select * from board order by num desc) b) "
				+ " ) where rn >= ?"
				+ " ) where rn <= ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  paging.getStartNum() );
			pstmt.setInt(2,  paging.getEndNum() );
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				BoardDto bdto = new BoardDto();
				bdto.setNum(rs.getInt("num"));
				bdto.setPass(rs.getString("pass"));
				bdto.setUserid(rs.getString("userid"));
				bdto.setTitle(rs.getString("title"));
				bdto.setEmail(rs.getString("email"));
				bdto.setContent(rs.getString("content"));
				bdto.setWritedate(rs.getTimestamp("writedate"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setImgfilename(rs.getString("imgfilename"));
				list.add( bdto );
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {  dbm.close(con, pstmt, rs);    }
		return list;
	}
	
	
	
	public int replyCount(int num) {
		int count = 0;
		String sql = "select count(*) as cnt from reply where boardnum=?";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt( 1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt("cnt");
		} catch (SQLException e) {	e.printStackTrace();
		} finally { dbm.close(con, pstmt, rs); }
		return count;
	}
	
	
	
	public void insert(BoardDto bdto) {
		
		String sql = "insert into board(num, pass, userid, email, title, content, imgfilename) "
				+ "values( board_seq.nextVal, ?,?,?,?,?,? )";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString( 1, bdto.getPass() );
			pstmt.setString( 2, bdto.getUserid() );
			pstmt.setString( 3, bdto.getEmail() );
			pstmt.setString( 4, bdto.getTitle() );
			pstmt.setString( 5, bdto.getContent() );
			pstmt.setString(6, bdto.getImgfilename());
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }
	}

	
	
	public BoardDto getBoard(int num) {
		BoardDto bdto = new BoardDto();
		String sql = "select * from board where num = ?";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bdto.setNum(rs.getInt("num"));
				bdto.setUserid(rs.getString("userid"));
				bdto.setPass(rs.getString("pass"));
				bdto.setEmail(rs.getString("email"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setWritedate(rs.getTimestamp("writedate"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setImgfilename(rs.getString("imgfilename"));
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }	
		return bdto;
	}

	
	
	public void plusReadCount(int num) {
		String sql="Update board set readcount = readcount +1 where num=?";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); 		}	
	}
	
	

	public ArrayList<ReplyVO> getReply(int num) {
		ArrayList<ReplyVO> list = new ArrayList<ReplyVO>();
		String sql = "select * from reply where boardnum=? order by replynum desc";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num );
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				ReplyVO rvo = new ReplyVO();
				rvo.setNum(rs.getInt("replynum"));
				rvo.setBoardnum( rs.getInt("boardnum") );
				rvo.setUserid(rs.getString("userid"));
				rvo.setWritedate(rs.getTimestamp("writedate") );
				rvo.setContent(rs.getString("content"));
				list.add(rvo);
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }
		return list;
	}

	
	
	public void addReply(ReplyVO rvo) {
		String sql = "insert into reply(replynum, boardnum, userid, content) values( reply_seq.nextVal, ?, ?, ?)";
		con = dbm.getConnection();
		try {			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  rvo.getBoardnum());
			pstmt.setString(2, rvo.getUserid());
			pstmt.setString(3, rvo.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) { 	e.printStackTrace();		
		} finally {	dbm.close(con, pstmt, rs); 		}	
	}

	
	
	public void deleteReply(int replynum) {
		String sql = "delete from reply where replynum = ?";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  replynum);
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { dbm.close(con, pstmt, rs);  }
	}


	
	public void boardUpdate(BoardDto bdto) {
		String sql = "update board set pass=? , userid=? , email=? , title=? , content=? , "
				+ "  imgfilename=? where num =?";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bdto.getPass() );
			pstmt.setString(2, bdto.getUserid() );
			pstmt.setString(3, bdto.getEmail() );
			pstmt.setString(4, bdto.getTitle() );
			pstmt.setString(5, bdto.getContent() );
			pstmt.setString(6, bdto.getImgfilename());
			pstmt.setInt(7, bdto.getNum() );
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally { dbm.close(con, pstmt, rs);		}
	}

	
	
	public void baordDelete(int num) {
		
		String sql = "delete from board where num=?";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  num );
			pstmt.executeUpdate();
		} catch (SQLException e) {	e.printStackTrace();
		} finally { dbm.close(con, pstmt, rs); }	
	}
}
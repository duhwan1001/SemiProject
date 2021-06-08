package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.MemberVO;

public interface MemberDAO {

	List<MemberVO> getMemberList(SqlSession session) throws SQLException;
	
	
}
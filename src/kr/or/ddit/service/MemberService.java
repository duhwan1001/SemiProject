package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.dto.MemberVO;

public interface MemberService {
	
	List<MemberVO> getMemberList() throws SQLException;

}

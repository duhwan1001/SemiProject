package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public List<MemberVO> getMemberList(SqlSession session) throws SQLException {
		System.out.println("memberDAO");
		List<MemberVO> members = null;
		try {
			members = session.selectList("Member-Mapper.getMemberList");
			System.out.println(members);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return members;
	}

}

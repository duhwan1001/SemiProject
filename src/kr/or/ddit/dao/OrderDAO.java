package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.OrderVO;

public interface OrderDAO {
	
	List<OrderVO> selectOrderSearchList(SqlSession session, String userId) throws SQLException;
	
	int selectOrderSearchListCount(SqlSession session, String userId) throws SQLException;
	
	
	
	
}

package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.OrderVO;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public List<OrderVO> selectOrderSearchList(SqlSession session, String userId) throws SQLException {
		List<OrderVO> orderList = session.selectList("Order-Mapper.selectOrderSearchList",userId);
		return orderList;
	}

	@Override
	public int selectOrderSearchListCount(SqlSession session, String userId) throws SQLException {
		int cnt = session.selectOne("Order-Mapper.selectOrderSearchListCount",userId);
		return cnt;
	}
	
	
	

}

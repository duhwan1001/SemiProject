package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.dao.OrderDAO;
import kr.or.ddit.dto.OrderVO;

public class OrderServiceImpl implements OrderService{

	SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	OrderDAO orderDAO;
	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	@Override
	public List<OrderVO> getOrderList(String userId) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			List<OrderVO> orderList = orderDAO.selectOrderSearchList(session, userId);
			return orderList;
		}finally {
			session.close();
		}
	}

	@Override
	public int getOrderListCount(String userId) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			int cnt = orderDAO.selectOrderSearchListCount(session, userId);
			return cnt;
		}finally {
			session.close();
		}		
	}
	
	

}

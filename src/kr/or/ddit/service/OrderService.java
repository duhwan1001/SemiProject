package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.dto.OrderVO;

public interface OrderService {
	
	List<OrderVO> getOrderList(String userId) throws SQLException;
	
	int getOrderListCount(String userId) throws SQLException;
	

}

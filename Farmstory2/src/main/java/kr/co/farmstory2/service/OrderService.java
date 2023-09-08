package kr.co.farmstory2.service;

import java.util.List;

import kr.co.farmstory2.dao.OrderDAO;
import kr.co.farmstory2.dto.OrderDTO;

public class OrderService {
	private static OrderService instance  = new OrderService();
	private OrderService() {}
	public static OrderService getInstance() {
		return instance;
	}
	
	private OrderDAO dao = OrderDAO.getInstance();
	
	
	public void insertOrder(OrderDTO dto) {
		dao.insertOrder(dto);
	}
	public OrderDTO selectOrder(String orderNo) {
		return dao.selectOrder(orderNo);
	}
	public List<OrderDTO> selectOrders() {
		return dao.selectOrders();
	}
	public void updateOrder(OrderDTO dto) {
		dao.updateOrder(dto);
	}
	public void deleteOrder(String orderNo) {
		dao.deleteOrder(orderNo);
	}
	
}

package summer.book_shop.service;

import org.springframework.stereotype.Service;
import summer.book_shop.domain.Order;

import summer.book_shop.exception.OrderException;
import summer.book_shop.exception.OrderExceptionType;
import summer.book_shop.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    //주문정보 조회
    public Order getOrderInfo(Order order) throws OrderException {
        if (!orderRepository.isOrderId(order.getOrderCode())) {
            throw new OrderException(OrderExceptionType.NOT_FOUND_ORDER_CODE);
        }

        return orderRepository.selectOrder(order.getOrderCode());
    }
    //주문 배송지 수정
    public void NewShippingAddress(String orderCode, String newAddress) throws OrderException {

        System.out.println("배송지 업데이트가 완료되었습니다.");

        orderRepository.updateshippingAddress(orderCode, newAddress);
    }

    //아이디별 총 주문건수
    public void orderOrderCnt(String orderId) throws OrderException {
        if (!orderRepository.isOrderId(orderId)) {
            throw new OrderException(OrderExceptionType.NOT_FOUND_ORDER_ID);
        }
        int orderCount = orderRepository.totalorder(orderId);
        System.out.println("회원님의 총주문 건수: " + orderCount);
    }

    // 주문 상태
    public void orderState(Order order) throws OrderException {
        if (orderRepository.save(order) == 1) {
            order.setStatus(true);
            System.out.println("주문 접수 완료되었습니다.");
        }

        if (!order.isStatus()) {
            order.setStatus(false);
            throw new OrderException(OrderExceptionType.WRONG_ORDER);
        }
    }
    //주문 취소
    public void cancelOrder(String orderCode) throws OrderException {
        if(orderRepository.orderdelete(orderCode) == 1){
            System.out.println("주문취소 되었습니다.");
        }
        if(orderRepository.orderdelete(orderCode) == 0){
            throw new OrderException(OrderExceptionType.CANNOT_DELETE_ORDER);
        }

    }
}
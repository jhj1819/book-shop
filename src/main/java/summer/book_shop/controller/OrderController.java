package summer.book_shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import summer.book_shop.domain.dto.OrderRequestDto;
import summer.book_shop.exception.OrderException;
import summer.book_shop.service.OrderService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    //주문 성공
    @PostMapping("/order/new")
    public ResponseEntity order(@RequestBody OrderRequestDto requestDto) {

        try {
            orderService.orderState(requestDto.toEntity());
        } catch (OrderException e) {
            log.info(e.getExceptionType().getErrorMessage());
            return new ResponseEntity(e.getExceptionType().getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("accepting orders", HttpStatus.OK);
    }
    //취소 성공
    @PostMapping("/order/delete")
    public ResponseEntity delete(String orderCode) {
        try {
            orderService.cancelOrder(orderCode);
            return new ResponseEntity("delete success", HttpStatus.OK);
        } catch (OrderException e) {
            System.out.println(e.getExceptionType().getErrorMessage());
            return new ResponseEntity(e.getExceptionType().getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    //주문 배송지 수정 성공
    @PostMapping("/shipping_address/update")
    public ResponseEntity update(@RequestBody String orderCode, String shipping_address) {
        try {
            orderService.NewShippingAddress(orderCode,  shipping_address);
            return new ResponseEntity("update success", HttpStatus.OK);
        } catch (OrderException e) {
            System.out.println(e.getExceptionType().getErrorMessage());
            return new ResponseEntity(e.getExceptionType().getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
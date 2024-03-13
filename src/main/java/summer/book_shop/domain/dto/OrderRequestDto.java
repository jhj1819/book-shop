package summer.book_shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import summer.book_shop.domain.Order;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderRequestDto {

    private String order_name; // PK
    private String shipping_address;
    private String bookCode;
    private int productCount;
    private String OrderCode;

    public Order toEntity() {
        return new Order(order_name,OrderCode, shipping_address, bookCode, productCount);
    }
}

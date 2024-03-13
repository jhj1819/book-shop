package summer.book_shop.repository;

import summer.book_shop.domain.Order;
import summer.book_shop.domain.dto.OrderRequestDto;

import java.sql.*;
import static org.apache.coyote.http11.Constants.a;

public class OrderRepository {

    private Connection conn; // 데이터베이스 연결, 쿼리 전송(insert, delete, update)
    private PreparedStatement pstmt; //
    private ResultSet rs;

    public OrderRepository() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String hostName = "localhost";
        String databaseName = "univDB";
        String utf8Connection = "?useUnicode=true&characterEncoding=utf8";

        String url = "jdbc:mysql://" + hostName + ":3306/" + databaseName + utf8Connection;
        String userName = "root";
        String password = "1234";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // update count 반환
    public int updateQuery(String sql) {
        int count;

        try {

            pstmt = (PreparedStatement) conn.createStatement();
            count = pstmt.executeUpdate(sql);
            return count;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    //주문번호 조회
    public boolean isOrderId(String orderCode) {
        String sql = "select orderCode from orders where orderCode = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderCode);
            return pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //주문목록 조회
    public Order selectOrder(String orderCode) {
        String sql = "select * from orders where orderCode = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderCode);

            rs = pstmt.executeQuery(sql);
            Order order = new Order();
            while (rs.next()) {
                order.setOrderCode(rs.getString(1));
                order.setOrderId(rs.getString(2));
                order.setOrder_name(rs.getString(3));
                order.setBookCode(rs.getString(4));
                order.setPhone_number(rs.getString(5));
                order.setTotalPrice(rs.getInt(6));
                order.setOrderDate(rs.getDate(7));
                order.setProductCount(rs.getInt(8));
                order.setTotalProductCount(rs.getInt(9));
                order.setPayment(rs.next());
                order.setStatus(rs.getBoolean(11));
                order.setShipping_address(rs.getString(12));
            }
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //주문취소
    public int orderdelete(String orderCode) {
        String sql = "delete from orders where orderCode = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderCode);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateQuery(sql);
    }

    //주문저장1
    //주문저장 ->jdbc 템플릿으로 코드 줄일 수 있음.
    public int save(Order order) {
        String sql = "insert into orders values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, order.getOrderId());
            pstmt.setString(2, order.getOrder_name());
            pstmt.setString(3, order.getBookCode());
            pstmt.setString(4, order.getPhone_number());
            pstmt.setInt(5, order.getTotalPrice());
            pstmt.setDate(6, (Date) order.getOrderDate());
            pstmt.setInt(7, order.getProductCount());
            pstmt.setInt(8, order.getTotalProductCount());
            pstmt.setString(9, order.getPayment());
            pstmt.setString(10, order.getOrderCode());
            pstmt.setBoolean(11, order.isStatus());
            pstmt.setString(11, order.getShipping_address());


            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateQuery(sql);
    }

    //아이디별 총 주문건수
    public int totalorder(String orderId) {
        int orderCount = 0;
        String sql = "select count(*) as 총_주문건수 from orders where orderId = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderId);
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                orderCount = rs.getInt("orderCount");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderCount;
    }

    //주문 수정(배송지 수정)
    public void updateshippingAddress(String orderCode, String newAddress) {
        String sql = "update orders set shipping_address = ? where orderCode = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderCode);
            pstmt.setString(2, newAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
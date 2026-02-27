package service.rentmanage;

import java.sql.Connection;

public interface Rentmanage {

    // --1) 주문 생성
    boolean createRent(Connection conn, long bookId, long userId);

    // // --2) 주문 수정
    boolean modifyRent(Connection conn, long bookId, long userId);

}

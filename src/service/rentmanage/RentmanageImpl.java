package service.rentmanage;

import java.sql.Connection;

import domain.rent.RentVO;
import repository.Rents;
import repository.RentsDAOImpl;

public class RentmanageImpl implements Rentmanage {

    Rents rentRepository = new RentsDAOImpl();

    @Override
    public boolean createRent(Connection conn, long bookId, long userId) {
        RentVO newRent = RentVO.builder()
                .bookId(bookId)
                .userId(userId)
                .build();
        return rentRepository.insertRent(newRent);
    }

    @Override
    public boolean modifyRent(Connection conn, long bookId, long userId) {
        RentVO newRent = RentVO.builder()
                .bookId(bookId)
                .userId(userId)
                .build();
        return rentRepository.modifyRent(newRent);
    }

}

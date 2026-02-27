package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import dbutil.DBUtil;
import domain.rent.RentVO;

public class RentsDAOImpl implements Rents {

    @Override
    public boolean insertRent(RentVO rent) {
        boolean result = false;
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "insert into rent (bookId, userId) "
                    + "values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, rent.getBookId());
            pstmt.setLong(2, rent.getUserId());

            if (pstmt.executeUpdate() != 0)
                result = true;

        } catch (SQLException e) {
            System.out.println("DB 처리 오류!! : " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean deleteRent(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRent'");
    }

    @Override
    public boolean modifyRent(RentVO rent) {
        boolean result = false;
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "update rent set returntime=?"
                    + "where bookid = ? and userid = ? and returntime is null";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setTimestamp(1,
                    new Timestamp(System.currentTimeMillis()));
            pstmt.setLong(2, rent.getBookId());
            pstmt.setLong(3, rent.getUserId());
            if (pstmt.executeUpdate() != 0)
                result = true;
        } catch (Exception e) {
            System.out.println("DB연동 실패 : " + e.getMessage());
        }
        return result;
    }

    @Override
    public List<RentVO> rentsList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rentsList'");
    }

    @Override
    public List<RentVO> ordersSearch(String loginId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ordersSearch'");
    }

}

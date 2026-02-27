package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dbutil.DBUtil;
import domain.users.UserVO;

public class UsersDAOImpl implements Users {

    @Override
    public int userAdd(UserVO user) {
        // insert 작업

        int result = 0; // 결과에 대한 반환 값 처리를 위한 변수

        try (Connection conn = DBUtil.getConnection()) {

            String sql = "insert into user "
                    + "(loginid, loginpw, userName, phone1, phone2, phone3) "
                    + "values(?,?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getLoginId());
            pstmt.setString(2, user.getLoginPw());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getPhone1());
            pstmt.setString(5, user.getPhone2());
            pstmt.setString(6, user.getPhone3());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DB작업 실패");
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public List<UserVO> userAll() {
        // slq select 전체
        List<UserVO> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection()) {

            // SQL
            String sql = "select * from user";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(UserVO.builder()
                        .userId(rs.getLong("userId"))
                        .loginId(rs.getString("loginId"))
                        .loginPw(rs.getString("loginPw"))
                        .userName(rs.getString("userName"))
                        .phone1(rs.getString("phone1"))
                        .phone2(rs.getString("phone2"))
                        .phone3(rs.getString("phone3"))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("DB 작업 실패!!");
            System.out.println(e.getMessage());
        }

        return list;
    }

    @Override
    public int userDel(UserVO user) {
        // sql delete
        int result = 0;
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "delete from user where userid = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, user.getUserId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DB 작업 실패!!");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int userMod(UserVO after) {
        // sql update
        int result = 0;
        try (Connection conn = DBUtil.getConnection()) {

            String sql = "update user set loginPw=?, userName=?," +
                    "phone1=?, phone2=?, phone3=?" +
                    " where userid = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, after.getLoginPw());
            pstmt.setString(2, after.getUserName());
            pstmt.setString(3, after.getPhone1());
            pstmt.setString(4, after.getPhone2());
            pstmt.setString(5, after.getPhone2());
            // pstmt.setTimestamp(9,
            // new Timestamp(System.currentTimeMillis()));
            pstmt.setLong(6, after.getUserId());
            System.out.println(pstmt);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DB 동작 에러!!");
            System.out.println(e.getMessage());
        }

        return result;
    }

    // @Override
    public Optional<UserVO> login(String loginId, String loginPw) {
        // sql select , where userId, userName
        Optional<UserVO> user = null;

        try (Connection conn = DBUtil.getConnection()) {

            // System.out.println("login start");

            // SQL
            String sql = "select * from user where loginId=? and loginPw=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, loginId);
            pstmt.setString(2, loginPw);
            ResultSet rs = pstmt.executeQuery();
            user = Optional.of(UserVO.builder()
                    .userId(-1)
                    .loginId("")
                    .loginPw("")
                    .userName("")
                    .phone1("")
                    .phone2("")
                    .phone3("")
                    .build());
            while (rs.next()) {
                user = Optional.of(UserVO.builder()
                        .userId(rs.getLong("userId"))
                        .loginId(rs.getString("loginId"))
                        .loginPw(rs.getString("loginPw"))
                        .userName(rs.getString("userName"))
                        .phone1(rs.getString("phone1"))
                        .phone2(rs.getString("phone2"))
                        .phone3(rs.getString("phone3"))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("DB 작업 실패!!");
            System.out.println(e.getMessage());
        }

        return user;
    }

    // @Override
    public Optional<UserVO> userSearch(String loginId) {
        // sql select, where email
        Optional<UserVO> result = null;

        try (Connection conn = DBUtil.getConnection()) {

            // SQL
            String sql = "select * from user where loginId=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, loginId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result = Optional.of(UserVO.builder()
                        .userId(rs.getLong("userId"))
                        .loginId(rs.getString("loginId"))
                        .loginPw(rs.getString("loginPw"))
                        .userName(rs.getString("userName"))
                        .phone1(rs.getString("phone1"))
                        .phone2(rs.getString("phone2"))
                        .phone3(rs.getString("phone3"))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("DB 작업 실패!!");
            System.out.println(e.getMessage());
        }

        return result;
    }

}

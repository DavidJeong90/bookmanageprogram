package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dbutil.DBUtil;
import domain.books.BookVO;

public class BooksDAOImpl implements Books {

    @Override
    public int bookAdd(BookVO book) {
        int result = 0; // 결과에 대한 반환 값 처리를 위한 변수

        try (Connection conn = DBUtil.getConnection()) {

            String sql = "insert into book "
                    + "(title, author, state) "
                    + "values(?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getState());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DB작업 실패");
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public int bookMod(BookVO book) {
        int result = 0;
        try (Connection conn = DBUtil.getConnection()) {

            String sql = "update book set title=?, author=?, state=?" +
                    " where bookid = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getState());
            pstmt.setLong(4, book.getBookId());
            // System.out.println(pstmt);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DB 동작 에러!!");
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public int bookDel(BookVO book) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bookDel'");
    }

    @Override
    public List<BookVO> bookAll() {
        // slq select 전체
        List<BookVO> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection()) {

            // SQL
            String sql = "select * from book";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(BookVO.builder()
                        .bookId(rs.getLong("bookid"))
                        .title(rs.getString("title"))
                        .author(rs.getString("author"))
                        .state(rs.getString("state"))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("DB 작업 실패!!");
            System.out.println(e.getMessage());
        }

        return list;
    }

    @Override
    public Optional<BookVO> searchBook(String title) {
        Optional<BookVO> result = null;

        try (Connection conn = DBUtil.getConnection()) {

            // SQL
            String sql = "select * from book where title=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result = Optional.of(BookVO.builder()
                        .bookId(rs.getLong("bookid"))
                        .title(rs.getString("title"))
                        .author(rs.getString("author"))
                        .state(rs.getString("state"))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("DB 작업 실패!!");
            System.out.println(e.getMessage());
        }

        return result;
    }

}

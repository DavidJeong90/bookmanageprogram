package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dbutil.DBUtil;
import dto.BookDTO;
import dto.UserDTO;

import service.usermanage.Usermanage;
import service.usermanage.UsermanageImpl;
import service.bookmanage.Bookmanage;
import service.bookmanage.BookmanageImpl;
import service.rentmanage.Rentmanage;
import service.rentmanage.RentmanageImpl;

public class BookProgramController {

    // private Ordermanage orderService = new OrdermanagImpl();
    private Usermanage userService = new UsermanageImpl();
    private Bookmanage bookService = new BookmanageImpl();
    private Rentmanage rentService = new RentmanageImpl();

    // 메서드 구현.
    // 회원 가입
    public boolean join(String loginId, String loginPw, String userName, String userPhone) {
        // 02-888-8888
        String phone1 = userPhone.substring(0, userPhone.indexOf('-'));
        String phone2 = userPhone.substring(userPhone.indexOf('-') + 1, userPhone.indexOf('-', 7));
        String phone3 = userPhone.substring(userPhone.indexOf('-', 7) + 1);

        // System.out.println("1: " + phone1 + " 2: " + phone2 + " 3: " + phone3);
        // phone1, phone2
        // System.out.println("phone1 : " + phone1);
        // System.out.println("phone2 : " + phone2);
        UserDTO userDTO = UserDTO.builder().loginId(loginId).loginPw(loginPw)
                .userName(userName).phone1(phone1).phone2(phone2).phone3(phone3).build();
        return userService.userRegister(userDTO);
    }

    // 회원 가입 정보 확인
    public UserDTO userInfo(String loginId) {
        return userService.searchOne(loginId);
    }

    // 회원 가입 정보 수정
    public boolean userModify(long userId, String loginId, String loginPw, String userName, String userPhone) {

        String phone1 = userPhone.substring(0, userPhone.indexOf('-'));
        String phone2 = userPhone.substring(userPhone.indexOf('-') + 1, userPhone.indexOf('-', 7));
        String phone3 = userPhone.substring(userPhone.indexOf('-', 7) + 1);

        // System.out.println("1: " + phone1 + " 2: " + phone2 + " 3: " + phone3);

        UserDTO userDTO = UserDTO.builder().userId(userId).loginId(loginId).loginPw(loginPw)
                .userName(userName).phone1(phone1).phone2(phone2).phone3(phone3).build();

        return userService.userModify(userDTO);
    }

    // 회원 탈퇴
    public boolean revokeUser(UserDTO userDTO) {
        // 기존 DB에 있는 사용자 정보 : user
        UserDTO user = userService.searchOne(userDTO.getLoginId());
        System.out.println("revokeUser : " + user);
        // DB에 있는 pw와 userDTO에 있는 pw를 비교해서 같으면, 삭제
        // 검증 처리
        if (user.getLoginPw().equals(userDTO.getLoginPw())) {
            return userService.userDelete(userDTO);
        }
        return false;

    }

    public boolean addBook(String title, String author) {
        BookDTO bookDTO = BookDTO.builder().title(title).author(author).state("Y").build();
        return bookService.bookRegister(bookDTO);
    }

    public List<BookDTO> BookList() {
        return bookService.searchAll();
    }

    public int rentBook(String title, long userId) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // 트랜잭션 시작

            // 도서 상태 확인
            BookDTO bookDTO = bookService.searchOne(title);
            if (bookDTO.getState().equals("N")) {
                System.out.println("대여중인 도서입니다.");
                return 0;
            } else {
                // 대여 기록 생성 (conn 전달 필수!)
                if (rentService.createRent(conn, bookDTO.getBookId(), userId)) {
                    // 도서 상태 변경 (conn 전달 필수!)
                    bookDTO.setState("N");
                    bookService.bookModify(bookDTO);

                    conn.commit(); // 모두 성공 시 커밋
                    return 1;
                } else
                    return 0;
            }

        } catch (Exception e) {
            // 하나라도 실패 시 롤백
            if (conn != null)
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                }
            return 0;
        } finally {
            // 자원 정리 및 오토커밋 복구
            if (conn != null)
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ex) {
                }
        }
    }

    public int returnBook(String title, long userId) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // 트랜잭션 시작

            // 도서 상태 확인
            BookDTO bookDTO = bookService.searchOne(title);
            if (bookDTO.getState().equals("Y")) {
                System.out.println("대여중이지 않은 도서입니다.");
                return 0;
            } else {
                // 대여 기록 생성 (conn 전달 필수!)
                if (rentService.modifyRent(conn, bookDTO.getBookId(), userId)) {
                    // 도서 상태 변경 (conn 전달 필수!)
                    bookDTO.setState("Y");
                    bookService.bookModify(bookDTO);

                    conn.commit(); // 모두 성공 시 커밋
                    return 1;
                } else
                    return 0;
            }

        } catch (Exception e) {
            // 하나라도 실패 시 롤백
            if (conn != null)
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                }
            return 0;
        } finally {
            // 자원 정리 및 오토커밋 복구
            if (conn != null)
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ex) {
                }
        }
    }

    // 로그인
    public UserDTO login(String loginId, String loginPw) {
        UserDTO userDTO = userService.login(loginId, loginPw);
        System.out.println("로그인시 정보 : " + userDTO);
        return userDTO;
    }

}

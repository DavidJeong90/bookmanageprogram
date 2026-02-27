package repository;

import java.util.List;
import java.util.Optional;

import domain.books.BookVO;

public interface Books {
    // 레코드 추가
    int bookAdd(BookVO book);

    // 레코드 수정
    int bookMod(BookVO bookVO);

    // 레코드 삭제
    int bookDel(BookVO book);

    // 레코드 조회
    // 1. 전체 조회
    List<BookVO> bookAll();

    // email(unique처리 안해도 unique)
    Optional<BookVO> searchBook(String title);
}
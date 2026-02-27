package service.bookmanage;

import java.util.List;

import dto.BookDTO;

public interface Bookmanage {
    // 1. 도서 등록
    boolean bookRegister(BookDTO bookDTO);

    // 2. 도서 수정
    boolean bookModify(BookDTO bookDTO);

    // 3. 도서 검색(특정 도서 검색, 전체 도서 검색)
    // 특정 도서
    BookDTO searchOne(String title);

    // 전체 도서
    List<BookDTO> searchAll();

    // 4. 도서 삭제
    boolean bookDelete(BookDTO bookDTO);

}

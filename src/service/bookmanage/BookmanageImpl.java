package service.bookmanage;

import java.util.ArrayList;
import java.util.List;

import domain.books.BookVO;
import dto.BookDTO;
import repository.Books;
import repository.BooksDAOImpl;

public class BookmanageImpl implements Bookmanage {
    Books bookRepository = new BooksDAOImpl();

    @Override
    public List<BookDTO> searchAll() {
        List<BookDTO> bookDTOList = new ArrayList<>();
        List<BookVO> bookVOList = bookRepository.bookAll();
        for (BookVO vo : bookVOList) {
            BookDTO dto = BookDTO.toBookDTO(vo);
            bookDTOList.add(dto);
        }
        return bookDTOList;
    }

    @Override
    public BookDTO searchOne(String title) {
        BookVO vo = bookRepository.searchBook(title).get();
        BookDTO dto = BookDTO.toBookDTO(vo);
        return dto;
    }

    @Override
    public boolean bookRegister(BookDTO bookDTO) {
        BookVO bookVO = BookDTO.toBookVO(bookDTO);
        if (bookRepository.bookAdd(bookVO) != 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean bookModify(BookDTO bookDTO) {
        BookVO bookVO = BookDTO.toBookVO(bookDTO);
        if (bookRepository.bookMod(bookVO) != 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean bookDelete(BookDTO bookDTO) {
        BookVO bookVO = BookDTO.toBookVO(bookDTO);

        if (bookRepository.bookDel(bookVO) != 0)
            return true;
        else
            return false;
    }
}

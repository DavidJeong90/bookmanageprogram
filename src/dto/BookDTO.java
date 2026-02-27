package dto;

import domain.books.BookVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private long bookId;
    private String title;
    private String author;
    private String state;

    public static BookVO toBookVO(BookDTO bookDTO) {
        return BookVO.builder()
                .title(bookDTO.title)
                .bookId(bookDTO.bookId)
                .author(bookDTO.author)
                .state(bookDTO.state)
                .build();
    }

    public static BookDTO toBookDTO(BookVO bookVO) {
        return BookDTO.builder()
                .bookId(bookVO.getBookId())
                .title(bookVO.getTitle())
                .author(bookVO.getAuthor())
                .state(bookVO.getState())
                .build();
    }

}

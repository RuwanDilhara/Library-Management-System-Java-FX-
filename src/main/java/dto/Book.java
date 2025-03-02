package dto;

import util.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    private String id;
    private String title;
    private String isbn;
    private String author;
    private Integer year;
    private String image;
    private Enum<BookStatus> status= BookStatus.AVAILABLE;
}

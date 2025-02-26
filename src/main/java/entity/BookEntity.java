package entity;

import util.enums.BookStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Books")
public class BookEntity {
    private String id;
    private String title;
    private String iSBM;
    private String author;
    private Integer year;
    private String image;
    private Enum<BookStatus> status= BookStatus.AVAILABLE;
}

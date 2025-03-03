package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.enums.BookStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Books")
public class BookEntity {
    @Id
    private String id;
    private String title;

    @Column(unique = true, nullable = false)
    private String isbn;

    private String author;
    private Integer year;
    private String image;

    @Enumerated(EnumType.STRING)
    private BookStatus status;
}

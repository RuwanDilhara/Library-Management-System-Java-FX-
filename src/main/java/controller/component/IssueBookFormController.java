package controller.component;

import controller.component.book.BookCardFormController;
import controller.component.borrow_book_details.BorrowBookDetailsFormController;
import controller.component.member.MemberCardFormController;
import dto.Book;
import dto.BorrowBookDetails;
import dto.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import service.custome.BookService;
import service.custome.BorrowBookDetailsService;
import service.custome.MemberService;
import service.custome.impl.BookServiceImpl;
import service.custome.impl.BorrowBookDetailsServiceImpl;
import service.custome.impl.MemberServiceImpl;
import util.enums.BookStatus;
import util.enums.MemberStatus;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class IssueBookFormController implements Initializable {
    private static IssueBookFormController instance;

    public static IssueBookFormController getInstance() {
        return instance == null ? instance = new IssueBookFormController() : instance;
    }

    public IssueBookFormController() {
        instance = this;
    }


    MemberService memberService = new MemberServiceImpl();
    BookService bookService = new BookServiceImpl();
    BorrowBookDetailsService borrowBookDetailsService = new BorrowBookDetailsServiceImpl();

    List<Member> memberList = memberService.getAll();
    List<Book> bookList = bookService.getAll();

    private static final int column_Count = 3;

    @FXML
    private Label lblMemberId;

    @FXML
    private ComboBox<String> cmbSearchType;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView bookImage;

    @FXML
    private ImageView imageUrl;

    @FXML
    private Label lblMemberAddress;

    @FXML
    private Label lblBookAuthor;

    @FXML
    private Label lblMemberContact;

    @FXML
    private Label lblMemberEmail;

    @FXML
    private Label lblMemberGender;

    @FXML
    private Label lblBookGen;


    @FXML
    private Label lblBookISBM;

    @FXML
    private Label lblMemberDate;

    @FXML
    private Label lblMemberName;

    @FXML
    private Label lblBookTitle;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField txtSearch;

    @FXML
    private Label lblBorrowBookCount;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblBookQty;

    private String recordId;

    private LocalDate isReturnDate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxLoader();
        loadMember();
        cardLoader();
        searchSuggester();
    }

    @FXML
    void btnReloadOnAction() {
        memberList = memberService.getAll();
        bookList = bookService.getAll();
        clearMemberCardDetails();
        clearBookCardDetails();
        loadBook();
        loadMember();
        lblBorrowBookCount.setText("#");
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    public void searchSuggester() {
        txtSearch.textProperty().addListener((observableValue, string, newValue) -> {
            if (!newValue.trim().isEmpty()) {
                if (cmbSearchType.getValue().equals("Member")) {
                    memberList = memberService.getMembersByName(newValue);
                    loadMember();
                } else {
                    bookList = bookService.searchByTitle(newValue);
                    if (bookList.isEmpty()) {
                        bookList = bookService.searchByAuthor(newValue);
                    }
                    loadBook();
                }
            } else {
                if (cmbSearchType.getValue().equals("Book")) {
                    bookList = bookService.getAll();
                    loadBook();
                } else {
                    memberList = memberService.getAll();
                    loadMember();
                }
            }

        });
    }

    public void cardLoader() {
        cmbSearchType.valueProperty().addListener((observableValue, string, t1) -> {
            if (t1.equals("Book")) {
                loadBook();
            } else {
                loadMember();
            }
        });
    }

    public void loadBook() {
        gridPane.getChildren().clear();
        int col = 0;
        int raw = 0;
        for (Book book : bookList) {
            if (book.getStatus().equals(BookStatus.AVAILABLE)) {
                bookAddToGrid(book, col, raw);
                col++;
                if (col == column_Count) {
                    raw++;
                    col = 0;
                }
            }
        }
    }

    public void bookAddToGrid(Book book, int col, int raw) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/book_card.fxml"));
        try {
            Parent root = loader.load();

            BookCardFormController controller = loader.getController();
            controller.setBookData(
                    book.getId(),
                    book.getTitle(),
                    book.getIsbn(),
                    book.getAuthor(),
                    book.getYear(),
                    book.getImage(),
                    book.getStatus(),
                    book.getQty()
            );

            gridPane.add(root, col, raw);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMember() {
        gridPane.getChildren().clear();
        int col = 0;
        int raw = 0;
        for (Member member : memberList) {
            if (member.getStatus().equals(MemberStatus.ACTIVE)) {
                memberAddToGrid(member, col, raw);
                col++;
                if (col == column_Count) {
                    raw++;
                    col = 0;
                }
            }
        }
    }

    public void memberAddToGrid(Member member, int col, int raw) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/member_card.fxml"));
        try {
            Parent root = loader.load();

            MemberCardFormController controller = loader.getController();
            controller.setMemberData(
                    member.getId(),
                    member.getName(),
                    member.getAddress(),
                    member.getMembershipDate(),
                    member.getContact(),
                    member.getEmail(),
                    member.getStatus(),
                    member.getBorrowedBookCount(),
                    member.getGender(),
                    member.getImageUrl()
            );

            gridPane.add(root, col, raw);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void comboBoxLoader() {
        ObservableList<String> searchTypes = FXCollections.observableArrayList();
        searchTypes.add("Member");
        searchTypes.add("Book");
        cmbSearchType.setValue("Member");

        cmbSearchType.setItems(searchTypes);
        cmbSearchType.valueProperty().addListener((observableValue, string, t1) -> {
            System.out.println(t1);
        });
    }

    public void setMemberCardDetails(String id, String name, String address, LocalDate membershipDate, String contact, String email, String memberType, int borrowBookCount, String imageUrl) {
        lblMemberId.setText(id);
        lblMemberName.setText(name);
        lblMemberAddress.setText(address);
        lblMemberDate.setText(membershipDate.toString());
        lblMemberContact.setText(contact);
        lblMemberEmail.setText(email);
        lblMemberGender.setText(memberType);
        this.imageUrl.setImage(new Image(imageUrl));

        lblBorrowBookCount.setText(String.valueOf(borrowBookCount));

    }

    public void setBookCardDetails(String id, String title, String author, String iSBM, String gen, String qty, String imageUrl) {
        lblBookId.setText(id);
        lblBookTitle.setText(title);
        lblBookAuthor.setText(author);
        lblBookISBM.setText(iSBM);
        lblBookGen.setText(gen);
        lblBookQty.setText(qty);
        bookImage.setImage(new Image(imageUrl));
    }

    public void btnDoneOnAction(ActionEvent actionEvent) {
        if (!lblBookId.getText().equals("--/--") && !lblMemberId.getText().equals("--/--")) {
            gridPane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/borrow_book_details_form.fxml"));

            if(Integer.parseInt(lblBorrowBookCount.getText())<3){
                try {
                    Parent root = loader.load();

                    BorrowBookDetailsFormController controller = loader.getController();

                    controller.setBorrowBookDetails(
                            generateId(),
                            lblMemberId.getText(),
                            lblMemberName.getText(),
                            lblBookId.getText(),
                            lblBookTitle.getText(),
                            LocalDate.now(),
                            Integer.parseInt(lblBorrowBookCount.getText())
                    );

                    gridPane.getChildren().add(root);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Please return borrowed books before borrowing a new one !").show();
                loadMember();
            }

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Please select member & book").show();
        }
    }

    public void clearBookCardDetails() {
        lblBookId.setText("--/--");
        lblBookAuthor.setText("--/--");
        lblBookTitle.setText("--/--");
        lblBookQty.setText("--/--");
        lblBookGen.setText("--/--");
        lblBookISBM.setText("--/--");
    }
    public void clearMemberCardDetails() {
        lblMemberId.setText("--/--");
        lblMemberAddress.setText("--/--");
        lblMemberGender.setText("--/--");
        lblMemberContact.setText("--/--");
        lblMemberEmail.setText("--/--");
        lblMemberDate.setText("--/--");
        lblMemberName.setText("--/--");
    }

    public boolean updateBookQty(String bookId){
        Book result = bookService.getAll().stream().filter(book ->
                book.getId().equals(bookId)).findFirst().orElse(null);
        if (result != null){
            result.setQty(result.getQty()-1);
            if (result.getQty()<=0){
                result.setStatus(BookStatus.UNAVAILABLE);
            }
            return bookService.updateBook(result);
        }
        return false;
    }
    public boolean updateMemberBorrowedBooksCount(String memberId){
        Member result = memberService.getAll().stream().filter(member ->
                member.getId().equals(memberId)).findFirst().orElse(null);
        if (result != null){
            result.setBorrowedBookCount(result.getBorrowedBookCount()+1);
            return memberService.updateMember(result);
        }
        return false;
    }
    public String generateId(){
        if (!borrowBookDetailsService.getAll().isEmpty()){
            String newId;
            int lastNumber = Integer.parseInt(borrowBookDetailsService.getAll().getLast()
                    .getRecordId()
                    .substring(1));
            newId= "R"+String.format("%03d", lastNumber + 1);
            System.out.println(lastNumber);
            return newId;
        }else {
            return "R001";
        }
    }
    public void saveBorrowBookDetail(BorrowBookDetails borrowBookDetails){
        boolean isSaved = borrowBookDetailsService.save(borrowBookDetails);
        if (isSaved){
            boolean isUpdatedMember = updateMemberBorrowedBooksCount(borrowBookDetails.getMemberId());
            boolean isUpdatedBook = updateBookQty(borrowBookDetails.getBookId());

            if (isUpdatedBook && isUpdatedMember){
                new Alert(Alert.AlertType.INFORMATION,"The book was successfully issued !").show();
            }
        }
        btnReloadOnAction();
    }

    @FXML
    private void btnClearAllOnAction(ActionEvent actionEvent) {
        btnReloadOnAction();
    }
}
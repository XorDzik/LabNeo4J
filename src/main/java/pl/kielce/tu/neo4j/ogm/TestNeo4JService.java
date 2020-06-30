package pl.kielce.tu.neo4j.ogm;

import java.util.Scanner;

public class TestNeo4JService {
    private BookService bookService;

    public TestNeo4JService(BookService bookService) {
        this.bookService = bookService;
    }

    public void addBook(){
        System.out.println("Podaj nazwisko autora książki: ");
        Scanner scanner = new Scanner(System.in);
        String authorSurname = scanner.nextLine();

        Author author1 = new Author(authorSurname);

        System.out.println("Podaj tytuł książki: ");
        String bookTitle = scanner.nextLine();

        Book book1 = new Book(bookTitle);
        book1.addAuthor(author1);

        bookService.createOrUpdate(book1);
    }

    public void printAllBooks(){
        System.out.println("All books:");
        for(Book b : bookService.readAll())
            System.out.println(b);
    }

    public void findBookById(){
        System.out.println("Podaj id książki którą chcesz znaleźć: ");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();

        for (Book b : bookService.readAll())
            if (b.getId().equals(id))
                System.out.println(b);
    }

    public void findBookByTitle() {
        System.out.println("Podaj tytuł książki którą chcesz znaleźć: ");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        for (Book b : bookService.readAll())
            if (b.getName().equals(title))
                System.out.println(b);
    }

    public void deleteBookById() {
        System.out.println("Podaj id książki którą chcesz usunąć: ");
        Scanner scanner = new Scanner(System.in);

        Long id = scanner.nextLong();
        bookService.delete(id);
    }

    public void updateBook() {
        System.out.println("Podaj id książki którą chcesz zaktualizować");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();

        System.out.println("Podaj tytuł książki: ");
        Scanner scanner2 = new Scanner(System.in);
        String bookTitle = scanner2.nextLine();

        System.out.println("Podaj nazwisko autora książki: ");
        String authorSurname = scanner2.nextLine();
        Author author1 = new Author(authorSurname);

        Book book1 = new Book(id, bookTitle);
        book1.addAuthor(author1);

        bookService.createOrUpdate(book1);
    }

}

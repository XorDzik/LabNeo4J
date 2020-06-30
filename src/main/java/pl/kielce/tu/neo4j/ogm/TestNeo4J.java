package pl.kielce.tu.neo4j.ogm;

import java.util.Map;
import java.util.Scanner;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class TestNeo4J {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration.Builder().uri("bolt://localhost:7687").credentials("neo4j", "neo4jpassword").build();
	    SessionFactory sessionFactory = new SessionFactory(configuration, "pl.kielce.tu.neo4j.ogm");

		Session session = sessionFactory.openSession();
		
		session.purgeDatabase();
			
		BookService bookService = new BookService(session);
		TestNeo4JService testNeo4JService = new TestNeo4JService(bookService);
		System.out.println("Aplikacja ma za zadanie pokazać działanie grafowych baz danych");
		System.out.println("Temat: biblioteka");
		for (;;) {
			System.out.println("1 -> Dodaj książkę");
			System.out.println("2 -> Aktualizuj dane o książce");
			System.out.println("3 -> Usuń książkę z biblioteki");
			System.out.println("4 -> Znajdź wszystkie książki");
			System.out.println("5 -> Znajdź książkę po id");
			System.out.println("6 -> Znajdź książkę po tytule");
			System.out.println("7 -> Zakończ program");

			Scanner scanner = new Scanner(System.in);
			String choice = scanner.nextLine();

			if (choice.equals("1"))
				testNeo4JService.addBook();

			if (choice.equals("2"))
				testNeo4JService.updateBook();

			if (choice.equals("3"))
				testNeo4JService.deleteBookById();

			if (choice.equals("4"))
				testNeo4JService.printAllBooks();

			if (choice.equals("5"))
				testNeo4JService.findBookById();

			if (choice.equals("6"))
				testNeo4JService.findBookByTitle();

			if (choice.equals("7"))
				break;
		}
	
		for(Map<String, Object> map : bookService.getBookRelationships())
			System.out.println(map);
		
		sessionFactory.close();
	}
}

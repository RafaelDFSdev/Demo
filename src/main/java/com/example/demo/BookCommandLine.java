package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BookCommandLine implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final Scanner scanner = new Scanner(System.in);

    public BookCommandLine(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean run = true;
        while (run) {
            System.out.println("Choose an option: \n1. Add Book \n2. List Books \n3. Loan Book \n4. Return Book \n5. Exit");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (option) {
                case 1:
                    addBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    loanBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void addBook() {
        System.out.println("Enter title:");
        String title = scanner.nextLine();
        System.out.println("Enter author:");
        String author = scanner.nextLine();
        bookRepository.addBook(title, author);
        System.out.println("Book added.");
    }

    private void listBooks() {
        bookRepository.listBooks().forEach(book ->
                System.out.println(book.getId() + " - " + book.getTitle() + " by " + book.getAuthor() + (book.isLoaned() ? " (Loaned)" : ""))
        );
    }

    private void loanBook() {
        System.out.println("Enter book ID to loan:");
        Long id = scanner.nextLong();
        bookRepository.updateLoanStatus(id, true);
        System.out.println("Book loaned.");
    }

    private void returnBook() {
        System.out.println("Enter book ID to return:");
        Long id = scanner.nextLong();
        bookRepository.updateLoanStatus(id, false);
        System.out.println("Book returned.");
    }
}
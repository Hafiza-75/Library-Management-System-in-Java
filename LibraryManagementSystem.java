import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;
    boolean isBorrowed;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public void borrow() {
        if (!isBorrowed) {
            isBorrowed = true;
            System.out.println("You borrowed \"" + title + "\".");
        } else {
            System.out.println("Sorry, \"" + title + "\" is already borrowed.");
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println("You returned \"" + title + "\".");
        } else {
            System.out.println("This book was not borrowed.");
        }
    }

    public String toString() {
        return "\"" + title + "\" by " + author + (isBorrowed ? " [Borrowed]" : " [Available]");
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Book added successfully.");
    }

    public void removeBook(String title) {
        boolean removed = books.removeIf(book -> book.title.equalsIgnoreCase(title));
        if (removed) {
            System.out.println("Book removed.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void searchBook(String keyword) {
        boolean found = false;
        for (Book book : books) {
            if (book.title.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books matched your search.");
        }
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void borrowBook(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                book.borrow();
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                book.returnBook();
                return;
            }
        }
        System.out.println("Book not found.");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        boolean running = true;

        while (running) {
            System.out.println("\n-------------------------  Library Menu:  ---------------------------");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. List All Books");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String titleAdd = sc.nextLine();
                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();
                    library.addBook(titleAdd, author);
                    break;
                case 2:
                    System.out.print("Enter book title to remove: ");
                    String titleRemove = sc.nextLine();
                    library.removeBook(titleRemove);
                    break;
                case 3:
                    System.out.print("Enter keyword to search: ");
                    String keyword = sc.nextLine();
                    library.searchBook(keyword);
                    break;
                case 4:
                    library.listBooks();
                    break;
                case 5:
                    System.out.print("Enter title to borrow: ");
                    String titleBorrow = sc.nextLine();
                    library.borrowBook(titleBorrow);
                    break;
                case 6:
                    System.out.print("Enter title to return: ");
                    String titleReturn = sc.nextLine();
                    library.returnBook(titleReturn);
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}

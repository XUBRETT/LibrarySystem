import java.util.ArrayList;
import java.util.List;

interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
    String toString();
}

class Book implements LibraryItem {
    private String title;
    private String author;
    private boolean borrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        if (!borrowed) {
            borrowed = true;
            System.out.println("Book \"" + title + "\" borrowed.");
        } else {
            System.out.println("Book \"" + title + "\" is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (borrowed) {
            borrowed = false;
            System.out.println("Book \"" + title + "\" returned.");
        } else {
            System.out.println("Book \"" + title + "\" is not currently borrowed.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String toString() {
        return "Book: \"" + title + "\", \"" + author + "\"";
    }
}

class DVD implements LibraryItem {
    private String title;
    private String director;
    private boolean borrowed;

    public DVD(String title, String director) {
        this.title = title;
        this.director = director;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        if (!borrowed) {
            borrowed = true;
            System.out.println("DVD \"" + title + "\" borrowed.");
        } else {
            System.out.println("DVD \"" + title + "\" is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (borrowed) {
            borrowed = false;
            System.out.println("DVD \"" + title + "\" returned.");
        } else {
            System.out.println("DVD \"" + title + "\" is not currently borrowed.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String toString() {
        return "DVD: \"" + title + "\" directed by \"" + director + "\"";
    }
}

abstract class LibraryUser {
    protected String name;
    protected List<LibraryItem> borrowedItems = new ArrayList<>();

    void borrowItem(LibraryItem item) {
        if (!item.isBorrowed()) {
            item.borrowItem();
            borrowedItems.add(item);
        }
    }

    void returnItem(LibraryItem item) {
        if (item.isBorrowed()) {
            item.returnItem();
            borrowedItems.remove(item);
        }
    }

    abstract void printItemsBorrowed();
}

class Student extends LibraryUser {
    public Student(String name) {
        this.name = name;
    }

    @Override
    void printItemsBorrowed() {
        System.out.println("STUDENT: " + name);
        System.out.println("BORROWED ITEM:");
        for (LibraryItem item : borrowedItems) {
            System.out.println("- " + item.toString());
        }
        System.out.println();
    }
}

class Teacher extends LibraryUser {
    public Teacher(String name) {
        this.name = name;
    }

    @Override
    void printItemsBorrowed() {
        System.out.println("TEACHER: " + name);
        System.out.println("BORROWED ITEM:");
        for (LibraryItem item : borrowedItems) {
            System.out.println("- " + item.toString());
        }
        System.out.println();
    }
}

public class LIBSYS {
    public static void main(String[] args) {
        // Book, DVD, Student, Teacher objects
        Book book = new Book("The Catcher in the Rye", "J.D. Salinger");
        Student student = new Student("Brett");
        student.borrowItem(book);

        DVD dvd = new DVD("Inception", "Christopher Nolan");
        Teacher teacher = new Teacher("Albert");
        teacher.borrowItem(dvd);

        // Simulate returning items
        book.returnItem();
        dvd.returnItem();

        // Print borrowed items
        student.printItemsBorrowed();
        teacher.printItemsBorrowed();
    }
}

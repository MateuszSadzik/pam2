package pl.nazwisko.lab5_start.comment;

/**
 * Model opisujący komentarz pobrany z Rest API.
 *
 * Created by Michał Ciołek.
 */
public class Comment {
    private int id;
    private String author;
    private String comment;

    public Comment(String author, String comment) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}


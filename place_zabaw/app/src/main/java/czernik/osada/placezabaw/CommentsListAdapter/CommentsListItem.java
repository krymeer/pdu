package czernik.osada.placezabaw.CommentsListAdapter;

public class CommentsListItem {
    private int authorID;
    private String authorName;
    private String date;
    private float rating;
    private String contents;

    public CommentsListItem(int authorID, String authorName, String date, float rating, String contents)
    {
        this.authorID   = authorID;
        this.authorName = authorName;
        this.date       = date;
        this.rating     = rating;
        this.contents   = contents;
    }

    float getRating() {
        return this.rating;
    }

    String getContents() {
        return this.contents;
    }

    String getDate() {
        return this.date;
    }

    String getAuthorName() {
        return this.authorName;
    }

    int getAuthorID() {
        return this.authorID;
    }
}

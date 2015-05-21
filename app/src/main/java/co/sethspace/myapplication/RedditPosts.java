package co.sethspace.myapplication;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Anirudh on 5/20/2015.
 */
public class RedditPosts {
    private String title;
    private int upvotes;
    private String author;

    public RedditPosts(String title, int upvotes, String author) {
        this.title = title;
        this.upvotes = upvotes;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpvotes() {
        return Integer.toString(upvotes);
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

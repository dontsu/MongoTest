package com.mongocourse.blog;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: draileanu
 * Date: 22/01/14
 * Time: 09:37
 */
public class BlogPostDAO {

    DBCollection postsCollection;

    public BlogPostDAO(final DB blogDatabase) {
        postsCollection = blogDatabase.getCollection("posts");
    }

    // Return a single post corresponding to a permalink
    public DBObject findByPermalink(String permalink) {

        DBObject post = null;
        BasicDBObject query = new BasicDBObject("permalink", permalink);
        post = postsCollection.findOne(query);
        return post;
    }

    // Return a list of posts in descending order. Limit determines
    // how many posts are returned.
    public List<DBObject> findByDateDescending(int limit) {

        List<DBObject> posts = new ArrayList<DBObject>();
        BasicDBObject query = new BasicDBObject("date", 1);
        DBCursor cursor = postsCollection.find().sort(query).limit(limit);
        if (cursor.hasNext()) {
            posts.add(cursor.next());
        }
        return posts;
    }


    public String addPost(String title, String body, List tags, String username) {

        System.out.println("inserting blog entry " + title + " " + body);

        String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
        permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
        permalink = permalink.toLowerCase();

        BasicDBObject post = new BasicDBObject();
        post.put("title", title);
        post.put("author", username);
        post.put("body", body);
        post.put("permalink", permalink);
        post.put("tags", tags);
        post.put("comments", new ArrayList<BasicDBObject>());
        post.put("date", new Date());
        postsCollection.insert(post);
        return permalink;
    }

    // Append a comment to a blog post
    public void addPostComment(final String name, final String email, final String body,
                               final String permalink) {
        BasicDBObject comment = new BasicDBObject();
        comment.put("author", name);
        comment.put("body", body);
        comment.put("date", new Date());
        if (email != null && !email.isEmpty()) {
            comment.put("email", email);
        }
        postsCollection.update(new BasicDBObject("permalink", permalink), new BasicDBObject("$set", comment), true, false);

    }

}


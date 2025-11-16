package data_access;

import entity.Post;
import entity.Comment;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;



import entity.User;
import entity.UserFactory;
import use_case.make_post.MakePostUserDataAccessInterface;
import use_case.search_user.SearchUserDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

public class DBUserDataAccessObject implements MakePostUserDataAccessInterface, SearchUserDataAccessInterface {

    private static final String STATUS_CODE_LABEL = "status_code";
    private static final int SUCCESS_CODE = 200;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String POST_ID = "post_id";
    private static final String POST_TITLE = "title";
    private static final String POST_BODY = "body";
    private static final String MESSAGE = "message";


    private final UserFactory userFactory;

    public DBUserDataAccessObject(UserFactory userFactory){
        this.userFactory = userFactory;
    }

    public void makePost(User user){
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);

        final JSONArray JSONPostArray = new JSONArray();

        ArrayList<Post> posts = user.getPosts();
        for (Post post : posts) {
            final JSONArray JSONCommentArray = new JSONArray();
            ArrayList<Comment> comments = post.getComments();
            for (Comment comment : comments) {
                JSONObject JSONCommentObject = new JSONObject()
                        .put("comment_id", comment.getComment_id())
                        .put("comment_body", comment.getBody())
                        .put("likes", comment.getLikes());
                JSONCommentArray.put(JSONCommentObject);
            }

            JSONObject JSONPost = new JSONObject()
                    .put(POST_ID, post.getPost_id())
                    .put(POST_TITLE, post.getTitle())
                    .put(POST_BODY, post.getBody())
                    .put("comments", JSONCommentArray);
            JSONPostArray.put(JSONPost);
        }

        JSONObject JSONInfo = new JSONObject()
                .put("bio", user.getBio())
                .put("email", user.getEmail())
                .put("posts", JSONPostArray);

        final JSONObject requestBody = new JSONObject()
                .put(USERNAME, user.getUsername())
                .put(PASSWORD, user.getPassword())
                .put("info", JSONInfo);

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    // This method needs to be implemented
    @Override
    public User findUserByUsername(String username) {
        return null;
    }
}
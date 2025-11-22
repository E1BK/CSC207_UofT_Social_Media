package data_access;

import entity.*;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import use_case.login_signup.change_passwrod.ChangePasswordUserDataAccessInterface;
import use_case.login_signup.login.LoginUserDataAccessInterface;
import use_case.login_signup.logout.LogoutUserDataAccessInterface;
import use_case.login_signup.signup.SignupUserDataAccessInterface;
import use_case.make_post.MakePostUserDataAccessInterface;
import use_case.my_profile.MyProfileUserDataAccessInterface;
import use_case.profile.ProfileUserDataAccessInterface;
import use_case.search_user.SearchUserDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

public class DBUserDataAccessObject implements MakePostUserDataAccessInterface,
        SearchUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LoginUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        ProfileUserDataAccessInterface,
        SignupUserDataAccessInterface{

    private static final String STATUS_CODE_LABEL = "status_code";
    private static final int SUCCESS_CODE = 200;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String POST_ID = "post_id";
    private static final String POST_TITLE = "title";
    private static final String POST_BODY = "body";
    private static final String POST_DATE = "post_date";
    private static final String COMMENT_LIKES = "comment_likes";
    private static final String COMMENT_ID = "comment_id";
    private static final String COMMENT_BODY = "comment_body";
    private static final String COMMENT_DATE = "comment_date";
    private static final String COMMENTS = "comments";
    private static final String MESSAGE = "message";


    private final UserFactory userFactory;
    private final PostFactory postFactory;
    private final CommentFactory commentFactory;

    private String currentUsername;

    public DBUserDataAccessObject(UserFactory userFactory, PostFactory postFactory, CommentFactory commentFactory){
        this.userFactory = userFactory;
        this.postFactory = postFactory;
        this.commentFactory = commentFactory;
        this.currentUsername = null;
    }


    @Override
    public void save(User user){
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);

        final JSONArray JSONPostArray = new JSONArray();

        ArrayList<Post> posts = user.getPosts();
        for (Post post : posts) {
            final JSONArray JSONCommentArray = new JSONArray();
            ArrayList<Comment> comments = post.getComments();
            for (Comment comment : comments) {
                JSONObject JSONCommentObject = new JSONObject()
                        .put(COMMENT_ID, comment.getComment_id())
                        .put(COMMENT_BODY, comment.getComment_body())
                        .put(COMMENT_DATE, comment.getComment_date())
                        .put(COMMENT_LIKES, comment.getLikes());
                JSONCommentArray.put(JSONCommentObject);
            }

            JSONObject JSONPost = new JSONObject()
                    .put(POST_ID, post.getPost_id())
                    .put(POST_TITLE, post.getTitle())
                    .put(POST_BODY, post.getBody())
                    .put(POST_DATE, post.getPost_date())
                    .put("comments", JSONCommentArray);
            JSONPostArray.put(JSONPost);
        }

        JSONObject JSONInfo = new JSONObject()
                .put("bio", user.getBio())
                .put("email", user.getEmail())
                .put("name", user.getName())
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

    @Override
    public boolean existsByName(String username) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            return responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE;
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User get(String username) {
        // For login, we might not need all the posts/comments
        // Return a basic user object with just username and password
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final String name = userJSONObject.getString(USERNAME);
                final String password = userJSONObject.getString(PASSWORD);

                return userFactory.create(name, password, "", "", "", new ArrayList<>());
            }
            else {
                return null; // User doesn't exist
            }
        }
        catch (IOException | JSONException ex) {
            return null; // Or throw exception based on your error handling
        }
    }

    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }

    @Override
    public void save(User user) {
        // TODO Implement
    }

    @Override
    public User getUserInfo(String req_username){
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);

        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", req_username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final String username = userJSONObject.getString(USERNAME);
                final String password = userJSONObject.getString(PASSWORD);

                final JSONObject infoJSONObject = userJSONObject.getJSONObject("info");
                final String bio = infoJSONObject.getString("bio");
                final String email = infoJSONObject.getString("email");
                final String name = infoJSONObject.getString("name");

                final JSONArray postsJSONArray = infoJSONObject.getJSONArray("posts");
                final ArrayList<Post> posts = new ArrayList<>();

                for  (int i = 0; i < postsJSONArray.length(); i++) {
                    final JSONObject postJSONObject = postsJSONArray.getJSONObject(i);

                    final int post_id = postJSONObject.getInt(POST_ID);
                    final String post_title = postJSONObject.getString(POST_TITLE);
                    final String post_body = postJSONObject.getString(POST_BODY);
                    final String post_date = postJSONObject.getString(POST_DATE);

                    final JSONArray commentsJSONArray = postJSONObject.getJSONArray(COMMENTS);
                    final ArrayList<Comment> comments = new ArrayList<>();
                    for  (int j = 0; j < commentsJSONArray.length(); j++) {
                        final JSONObject commentJSONObject = commentsJSONArray.getJSONObject(j);
                        final int comment_id = commentJSONObject.getInt(COMMENT_ID);
                        final String comment_body = commentJSONObject.getString(COMMENT_BODY);
                        final String comment_date = commentJSONObject.getString(COMMENT_DATE);
                        final int comment_likes = commentJSONObject.getInt(COMMENT_LIKES);

                        final Comment comment = commentFactory.create(comment_id, comment_body, comment_date, comment_likes);
                        comments.add(comment);
                    }
                    final Post post = postFactory.create(username, post_id, post_title, post_body, post_date, comments);

                }
                return userFactory.create(username, password, bio, email, name, posts);
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void changePassword(User user) {

    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }
}
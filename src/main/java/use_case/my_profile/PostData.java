package use_case.my_profile;

import entity.Post;
import entity.User;
import interface_adapter.my_profile.MyProfileViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostData {
    private MyProfileUserDataAccessInterface myProfileUserDataAccess;
    private MyProfileOutputBoundary myProfilePresenter;
    private ArrayList<Map> postList;

    public PostData() {
        postList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            HashMap<String, String> postInfo = new HashMap<>();
            postInfo.put(MyProfileViewModel.TITLE, STR."Post \{i}");
            postInfo.put(MyProfileViewModel.BODY, STR."This is the \{i} post!");
            postInfo.put(MyProfileViewModel.DATE, "00/00/00");
            postInfo.put(MyProfileViewModel.ID, "0000");
            postList.add(postInfo);
        }
    }

    public PostData(MyProfileUserDataAccessInterface myProfileUserDataAccessInterface,
                    MyProfileOutputBoundary myProfilePresenter) {
        this.myProfileUserDataAccess = myProfileUserDataAccessInterface;
        this.myProfilePresenter = myProfilePresenter;
    }

    public void setPostList(MyProfileInputData myProfileInputData) {
        String username = myProfileInputData.getUsername();
        User user;

        try {
            user = myProfileUserDataAccess.getUserInfo(username);
        } catch (Exception e) {
            // makePostPresenter.prepareFailView("Failed to load user: " + e.getMessage());
            return;
        }

        ArrayList<Post> userPosts = user.getPosts();
        postList = new ArrayList<>();

        setPostList(userPosts);
    }

    public void setPostList(ArrayList<Post> userPosts) {
        ArrayList<Map> tempPostList = new ArrayList<>();

        int loops = userPosts.size();

        if (loops > 3) {
            loops = 3;
        }

        for  (int i = 1; i <= loops; i++) {
            HashMap<String, String> postInfo = new HashMap<>();
            postInfo.put(MyProfileViewModel.TITLE, userPosts.get(userPosts.size() - i).getTitle());
            postInfo.put(MyProfileViewModel.BODY, userPosts.get(userPosts.size() - i).getBody());
            postInfo.put(MyProfileViewModel.DATE, userPosts.get(userPosts.size() - i).getPost_date());
            postInfo.put(MyProfileViewModel.ID, STR."\{userPosts.get(userPosts.size() - i).getPost_id()}");
            tempPostList.add(postInfo);
        }

        postList = tempPostList;
    }

    public ArrayList<Map> getPosts() {
        return postList;
    }
}
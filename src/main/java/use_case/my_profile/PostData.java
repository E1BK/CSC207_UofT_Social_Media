package use_case.my_profile;

import entity.Post;
import interface_adapter.my_profile.MyProfileViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostData {
    private ArrayList<Map> postList;

    public PostData() {
        postList = new ArrayList<>();
    }

    public void setPostList(ArrayList<Post> userPosts) {
        ArrayList<Map> tempPostList = new ArrayList<>();

        int loops = userPosts.size();

        for (int i = 1; i <= loops; i++) {
            HashMap<String, String> postInfo = new HashMap<>();
            postInfo.put(MyProfileViewModel.USERNAME, userPosts.get(userPosts.size() - i).getUsername());
            postInfo.put(MyProfileViewModel.TITLE, userPosts.get(userPosts.size() - i).getTitle());
            postInfo.put(MyProfileViewModel.BODY, userPosts.get(userPosts.size() - i).getBody());
            postInfo.put(MyProfileViewModel.DATE, userPosts.get(userPosts.size() - i).getPost_date());
            postInfo.put(MyProfileViewModel.ID, STR."\{userPosts.get(userPosts.size() - i).getPost_id()}");
            postInfo.put(MyProfileViewModel.NUM_OF_COMMENTS, STR."\{userPosts.get(userPosts.size() - i).getComments().size()}");
            tempPostList.add(postInfo);
        }

        postList = tempPostList;
    }

    public ArrayList<Map> getPosts() {
        return postList;
    }
}

package use_case.make_post;

public class MakePostInputData{
    private final String username;
    private final String title;
    private final String body;

    public MakePostInputData(String username, String title, String body){
        this.username = username;
        this.title = title;
        this.body = body;
    }

    public String getUsername(){ return username; }
    public String getTitle(){ return title; }
    public String getBody(){ return body; }

}
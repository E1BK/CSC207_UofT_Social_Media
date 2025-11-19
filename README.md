# CSC207_UofT_Social_Media
A social media app for the UofT community that allows students to login, make posts, see others’ posts, and communicate.

# Use Stories
1. I would like to sign up with my username, password, and UofT Email.
2. I would like to log in with my username and password.
3. On the Landing Page, I would like to see:
   - 3 recent posts
   - a button for “People”
   - a button for “Me”
   - a button to “Make a Post”
4. By clicking on the “Me” button on the Landing Page, I would like to be able to go to my Profile Page, where I can update my profile and bio.
5. By clicking the “Make a Post” button on the Landing Page, I would like to be able to create a post.
6. By clicking the “People” button on the Landing Page, I would like to be taken to a Find People page, where I can search for users by their username.
7. On the Find People Page, I would like to search for a user: if the user exists, I would like to see their profile; otherwise, I would like to see a “User Not Found” display.
8. If I click on a user’s profile after searching for them, I would like to see all their posts and their bio.
9. By clicking on a post I would like to view that post and the comments under it.
10. I would like to leave comments on the post and like other comments.

# Use Cases:

Use Case 1: Log-in/sign-up
- Log-in Flow:
  - User Opens log-in page
  - User presses log in
  - User fills out all necessary details
    - Username
    - Password
  - User is sent to the home page
- Log-in Alternate Flow:
    - User opens log-in page
    - User presses log in
    - User doesn’t fill out all details / gets details wrong
    - User is told that the user could not be found
- Sign-up Flow:
    - User opens sign-up page
    - User presses sign up
    - User fills in all necessary details
      - Name/Email/Username/Password
    - User is told that a new user is created and sent to the sign-in page
    - User signs in and gets sent to the home page
- Sign-up Alternate Flow:
    - User opens sign-up page
    - User presses sign up
    - User does not fill in all details
    - User is told that the new user could not be created

Use Case 2: Landing Page
- Main Flow:
  - User sees a "search" button to search for people
  - User sees a "me" button for profile page of him/herself
  - User sees 3 random posts
  - User sees a "Make Post" Button to start a new post
- Alternate Flow:
  - filler

Use Case 3: Profile and Bio
- Main Flow:
  - User clicks on the “me” button in landing page
  - User sets his/her Name
  - User sets his/her Bio
- Alternate Flow:
  - filler

Use Case 4: Making Posts
- Main Flow:
  - filler
- Alternate Flow:
  - filler

Use Case 5: Search for People
- Main Flow:
  - Users click the search button on the home page
  - User types in the name of the user they wish to find in the search bar
  - User presses search
  - List of users whose name matches or partially matches the name searched
  - User clicks on a user and is sent to their profile page
- Alternate Flow:
  - filler

Use Case 6: view Post/Comment
- Main Flow:
  - filler
- Alternate Flow:
  - filler

# Minimum Viable Product Table:

| Use Case                 | User Story          | Lead Developer |
|--------------------------|---------------------|----------------|
| Login, Signup, Sign out  | User Story 1 and 2  | Mike           |
| Landing Page             | User Story 3        | Hasan          |
| Profile and Bio          | User Story 4 and 8  | Julian         |
| Make a Post              | User Story 5        | Hayden         |
| Search system for people | User Story 6 and 7  | Russell        |
| view Post/Comment        | User Story 9 and 10 | Ioane          |

# API Specification

We will be using the Grade API (http://vm003.teach.cs.toronto.edu:20112/user) for permanent storage of User data.

In the Grade API, the `info` field allows for any arbitrary JSON to be stored, the base format JSON looks like:

```json
{
    "username": "yourdesiredusername2", 
    "password": "newpassword",
    "info": {
        
    }
}
```

To store extra information we need, such as the bio or posts, we add into the `info` field, as such:

```json
{
    "username": "zhaohayd", 
    "password": "omgmypasswordisleaking",
    "info": {
        "bio": "this is a bio",
        "email": "hayden.zhao@mail.utoronto.ca",
        "name": "Hayden HaoDong Zhao",
        "posts": [
            {
                "post_id": 1,
                "post_title": "this is a title",
                "post_body": "this is the body",
                "post_date": "2025-11-16T17:05:32.123",
                "comments": [
                    {
                        "comment_id": 1,
                        "comment_date": "2025-11-18T17:05:32.123",
                        "comment_body": "this is a comment",
                        "comment_likes": 124
                    }
                ]
            },
            {
                "post_id": 2,
                "post_title":"this is a title",
                "post_body": "this is the body",
                "post_date": "2025-11-17T17:05:32.123",
                "comments": [
                    {
                        "comment_id": 1,
                        "comment_date": "2025-11-17T17:05:32.123",
                        "comment_body": "this is another comment",
                        "comment_likes": 4566
                    },
                    {
                        "comment_id": 2,
                        "comment_date": "2025-11-27T17:05:32.123",
                        "comment_body": "this is another 111111 comment",
                        "comment_likes": 43
                    }
                ]
            }
        ]
    }
}
```

`username` should be a UTORiD, since this cannot be enforced, it should still be checked that usernames do not overlap on signup. This is also to prevent repeatedly signing up from the same user.

`password` can be kept as-is for now, unless we figure something out to encrypt it?

`email` should be an address that ends with "@mail.utoronto.ca", this should be enforced by checking the string.

`post_date` and `comment_date` should be created with `LocalDateTime.now().toString()`

Note that all fields should be checked for illegal characters.

Additionally, we also need a "*repository*" user to store all the users that currently exist, we define a permanent `user` entity in the DB, and store usernames inside `info`, as such:

```json
{
    "username": "USER_REPO_CTG3",
    "password": "CTG3CTG3",
    "info": {
        "users": [
            {
                "username": "zhaohayd",
                "name": "Hayden HaoDong Zhao"
            },
            {
                "username": "zhaochri",
                "name": "Christie HaoJing Zhao"
            }
        ]
    }
}

```

# Entities

## User

### Fields

username: String, username of user, must be a utorid?

password: String, password of user

email: String, email of user, must end with @mail.utoronto.ca

posts: ArrayList\<Post>, list of posts of user

## Post

### Fields

post_id: String, id of post

title: String, title of post

body: String, body of post
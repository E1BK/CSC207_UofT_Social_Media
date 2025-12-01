# CSC207_UofT_Social_Media
A social media app for the UofT community that allows students to login, make posts, see others’ posts, and communicate.

# Use Stories
1. I would like to sign up with my utorID, password, and UofT Email.
2. I would like to log in with my utorID and password.
3. As a logged-in user, I would like to make a post.
4. As a logged-in user, I would like to view recent posts.
5. As a logged-in user, I would like to search and get information about a particular club at UofT.
6. As a logged-in user, I would like to see my own posts.
7. As a logged-in user, I would like to see my profile and modify my password and/or bio.
8. As a logged-in user, I would like to search for a particular user and see their profile and posts.
9. As a logged-in user, I would like to like a post.
10. As a logged-in user, I would like to comment on a post.


# Minimum Viable Product Table:

| Use Case                 | User Story          | Lead Developer |
|--------------------------|---------------------|----------------|
| Login, Signup, Sign out  | User Story 1 and 2  | Mike           |
| Landing Page, Clubs      | User Story 3 and 5  | Hasan          |
| Profile and Bio          | User Story 6 and 7  | Julian         |
| Make a Post              | User Story 3 and 4  | Hayden         |
| Search system for people | User Story 8        | Russell        |
| Like Post/Comment        | User Story 9 and 10 | Ioane          |

# Use Cases:

Use Case 1: Log-in/sign-up
- Log-in Flow:
  - User Opens log-in page
  - User presses log in
  - User fills out all necessary details
    - UtorID
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
  - User sees a "People" button to search for people
  - User sees a "Me" button for profile page of him/herself
  - User sees 3 recent posts
  - User sees a "Make Post" Button to start a new post
  - User sees a "See Clubs" button to search for clubs

Use Case 3: Profile and Bio
- Main Flow:
  - User clicks on the “me” button in landing page
  - User sets his/her Name
  - User sets his/her Bio
  - User can change his/her password
  - User sees his/her posts
  - User can like posts
  - User can comment on posts

Use Case 5: Search for People
- Main Flow:
  - Users click the search button on the home page
  - User types in the name of the user they wish to find in the search bar
  - User presses search
  - List of users whose name matches or partially matches the name searched
  - User clicks on a user and is sent to their profile page

Use Case 6: Search for Clubs
- Main Flow:
   - User types in a club name
   - If the club exists, a description of the club is shown
   - Otherwise an error message is shown


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

`post_date` and `comment_date` should be created with `Instant.now().toString();`

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

Similarly, we further define a repository user for storing clubs:

```json
{
    "username": "CLUB_REPO_CTG3",
    "password": "CTG3CTG3",
    "info": {
        "clubs": [
            {
                "club_name": "UTRG",
                "club_description": "The University of Toronto Rhythm Game Club"
            },
            {
                "club_name": "PhySU",
                "club_description": "Physics Student Union"
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

## Comment

### Fields

## Club

### Fields

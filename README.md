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

Use Case 3: See and Update Your Own Profile
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

Use Case 6: View Peoples Profiles
- Main Flow:
  - filler
- Alternate Flow:
  - filler

# Minimum Viable Product Table:


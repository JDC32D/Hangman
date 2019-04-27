Hangman
Create the classic game of hangman where a user has X number of attempts to guess
the correct word.
Main Page - Title Screen
1. User is presented with a “Title” screen to start the game
1. Of course, a start button to start the game
2. A Large title for the game, possibly a graphic if you can make one
3. A drawn image of “hangman”
#Game Page
1. User is presented with the keyboard and blank spaces where the letters will go for
the phrase (this cannot be a single word for the round)
2. When a phrase is given, the words should be spaced out so that it does not look like
one long word.
1. Ex. “I like Toast” should be _ _ _ _ _ _ _ _ _ _ and not _ _ _ _ _ _ _ _ _ _
3. When the user selects an incorrect letter, a body part is drawn
4. Used letters are displayed on the screen
5. If the hangman is completed, the user loses the game and is presented with an
opportunity to play again.
1. The phrase should be filled out in the blanks if the user did not complete the
phrase within the allowed number of attempts
2. There needs to be some indication that the user failed.
6. The user should be taken back to the Main Page when the game is over if they
choose not to play again
1. If the user chooses to play again, you should immediately take them into the
game and not back to the Main Page.
7. The user is allowed to end the game at any point and be taken back to the Main
Page
1. If a user chooses to do this they need to be “chicken tested” aka: “Are you
sure you want to quit?”
2. They should not be taken back until they answer the “Chicken Test”
Notifications
1. You must implement local notifications.
2. Setup a local notification that can be generated within 1 minute (5 if the system
does not allow) of closing the application.
3. Create a message in this to “convince” the user they should return to your app.
Additional Requirements
4. The stick figure hangman needs to be drawn with either a Canvas/View, or with
drawables. NO PREDEFINED IMAGES!!
5. Orientation is not necessary. However, if it is not supported, it must be locked (can
be portrait or landscape locked)
6. Good MVC design patterns of course
7. The app should not continue to add to the View Stack (I should only be able to hit
back twice to completely close this app)
8. Your “phrase bank” must have at least 10 phrases
Try to create your “hangman” draw code in its own class. You should be able to achieve
this on a Canvas easy enough. If done properly you can git away with using the exact
same Canvas code, without duplication, on the Main Page and on the Game Page.
If we are unable to get to the content soon enough I will do what I can to post slides and
example code of topics that I am hoping to cover to keep everyone on track.

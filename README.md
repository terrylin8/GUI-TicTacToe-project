# Tic Tac Toe

## For two players to play a set of games

This **Tic Tac Toe** game allows two users to have a few rounds of mini games and save their scores.
The reason for designing this game is that I am a board game enthusiast myself, 
and I *really* want to start learning how to make a board game of mine from the basic ones.
Tic Tac Toe then came to my mind as a starting point. 

##*User Stories*:
- As a user, I want to be able to add pieces to the game board //adding multiple Xs to a Y
  - GameBoard.putPiece()
  
- As a user, I want to be able to set the icon of my piece to anything other than default 
  - Player.setIcon()
  
- As a user, I want to be able to see the current status(name, icon, score) of both players 
  - Player.currentStatus()
  
- As a user, I want to be able to restart the games, discarding all previous scores 
  - Games.startOver()
  
- As a user, I want to be able to save the player information (name, score, icon)

- As a user, I want to be able to reload the player information stored

- Phase 4: Task 2: the class GameBoard and method putPiece() is now robust with a NotInBoardException 
handling exceptions when putPiece() try to place pieces out of the array range of GameBoard
  - GameBoard.putPiece()
  
- Phase 4: Task 3: If I had more time for this project, I would merge Game into GameBoard
 since I find the Game class quite redundant after drawing the UML diagram. I realized that
the Game class would only ever have 2 players in it and both classes (GUI and GameWindow) 
that are associated with Game have 2 players in them already. The removal of the Game class 
while combining its functionality into the GameBoard class would improve my design by 
simplifying it.  

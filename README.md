# Tic Tac Toe

## For two players to play a set of games

This **Tic Tac Toe** game will create a game board for two users to play on.
For phase 1 of the project, this game will not show a graphic board, but texts only instead.
The reason for designing this game is that I am a board game enthusiast myself, 
and I *really* want to start learning how to make a board game of mine from the basic ones.
Tic Tac Toe then came to my mind as a starting point. 

##*User Stories*:
- As a user, I want to be able to add pieces to the game board //adding multiple Xs to a Y
  - gameBoard.putPiece()
  
- As a user, I want to be able to set the icon of my piece to anything other than default 
  - Player.setIcon()
  
- As a user, I want to be able to see the current status(name, icon, score) of both players 
  - Player.currentStatus()
  
- As a user, I want to be able to restart the games, discarding all previous scores 
  - Games.startOver()


- *Extra idea for later on*: adding more grid to expand the game into something like **Gobang** 
where you connect 5 pieces in line to win
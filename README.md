# CS3398--Maggia--F2019

# Chess++

> This will be our redesigned chess game that will include additional pieces, goals, unlocks, as well as extended boards. We will begin by making the standard game of chess at first. Eventually, our team, Guillermo Gomez, Roy Grady, Kody Davis, Felipe Rodriguez, and Kieran Hsieh, will add more pieces, spaces, goals, and achievements in-game. Our goal is to test our own personal skills by creating something similar yet different. This project is for chess enthusiasts who want some variety in their gameplay. We hope to create an interesting and dynamic chess game that will be user friendly, challenging, fun, and unique.  

## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Inspiration](#inspiration)
* [Contact](#contact)
* [Sprint 1 Review](#Sprint-1-Review)

## General info

> We wish we had the knowledge and experience to implement this game into a web browser with online functionality.

## Screenshots
![Example screenshot](https://i.pinimg.com/736x/98/7b/0b/987b0b6fcc6987ada88a448b2ddbe10a--cool-wallpaper-chess.jpg)

## Technologies
* Tech 1 - JRE version 1.8
* Tech 2 - version 2.0
* Tech 3 - version 3.0

## Setup
Describe how to install / setup your local environement / add link to demo version.

## Code Examples
Show examples of usage:
`put-your-code-here`

## Features
List of features ready and TODOs for future development       
* - Fundamental movement/position mechanics of the chess engine 
  - Implementing classic chess pieces 
  - Menus 
  - Player info
  - Board graphics and chess piece sprites

To-do list:
* Online functionality 

  -        Implement client and server functions to make playing between networks possible.
           
* Improve GUI feautures

  -        Make the GUI more intuitive by adding drag-and-drop, more highlight situations, and more.
           
* Option and profile menus

  -        Flesh out the options menu to change the properties of the game and add a menu for user profiles.
           
* Chess replay

  -        Save the move history in a way that allows a user to replay a finished match.
           
* Add new modes

  -        Add a new mode where the user can place the pieces on the board themself, a mode with different
           board sizes, and more.
           
## Status
Project is: in progress. Sprint 1 is complete. See each member's status down in Sprint 1 Review. Starting Sprint 2.

## Inspiration
Add here credits. Project inspired by..., based on...
-     "Characters" Icons made by Eucalyp from www.flaticon.com
-     "Characters2" Icons made by Freepik from www.flaticon.com

## Contact
Created by [Guillermo Gomez](g_g224@txstate.edu) 
           [Roy Grady](rag189@txstate.edu)
           [Felipe Rodriguez](f_r95@txstate.edu)
           [Kody Davis](kody_davis@txstate.edu)
           [Kieran Hsieh](kth43@txstate.edu)

- feel free to contact us!

## Sprint 1 Review

* Roy Grady

  -        I created the code for the board representation as well as all of the computations for
           the possible moves the pieces can make. I also managed how the board recognizes where
           the pieces are and the conditions for absolute pins, interference squares, and checkmate.
           My code in GitHub is everything in Board.java, WhitePawn.java, WhiteBishop.java,
           WhiteKnight.java, WhiteRook.java, WhiteQueen.java, WhiteKing.java, BlackPawn.java, 
           BlackBishop.java, BlackKnight.java, BlackRook.java, BlackQueen.java, BlackKing.java, 
           and PawnPromotion.java. The main branch I used when updating my code was in RoyTemp.
  
  -        My next step is to implement online functionality and allow for multiple board sizes,
           as well as finish the remaining classic chess functions like stalemates, castling, and en passant.
           
* Kieran Hsieh

  -        Completed the chess GUI, which can be found in ChessGUI.java under the Board branch. This was used to visually represent 
           the board, and was used in the Menu as something that opened after the play button was clicked.
  -        My next step will be to connect profiles and users to the GUI
           
* Guillermo Gomez

  -        I created the basic main menu interface for the chess game. I implemented the standard play button as well as other optional
           buttons such as settings and profile. This menu can be found under the Menu.java class, using the Button class to create the
           buttons in the menu. All these classes are under the Menu branch. 
           
* Kody Davis

  -        I helped with the construction of the GUI by finding pictures of chess pieces as well as a way
           to read them in and show them on the board. I also added the menu bar with the options button on top
           of the board as a way to change the color of the board for player customization. Only one color can
           be selected at a time and one color must always be selected. The branch I used to track my changes is
           called KodyGUI.
  -        My next step will be to implement a color wheel or some sort of color choosing mechanism that will 
           allow the user to have a greater selection of colors. I would also like to find different models for
           the chess pieces that can be changed in the settings menu. One last thing I will work towards is the
           implementation of a save option that will allow users to resume a game in progress.
           
* Felipe Rodriguez

  -        The user profile has been completed as a constructor that will inititate the players ELO score along 
           all other information such as name, username, date of birth, etc. 
           
           Things I will be working on:
           - I will get with guillermo to implement the user profile option in the menu 
           - Create a customizable timer for players to use 
           - Creating an ELO grading system that will be integrated in user profiles 
           - add a playlist feature that will allow users to play their own music or go with the classical music integrated
           
           
           Things I am thinking about incorperating 
           - a feature that shows the odds of a player winning without music then comparing to results once music is played
           - a news feed to let competative players know when there is an in person tournament going on via zipcode 
           
## Sprint 2

* Roy Grady
* Kieran Hsieh
  -         Implemented the builder piece and the new game modes after refactoring the basic GUI code. 
            This is shown by the files MainBoardPanel.java, ChessGui.java, BulletChessGui.java, ChessGuiPlus.java, 
            GenericBoardPanel.java and it's offshoots, HistoryPanel.java, and InfoPanel.java.
* Guillermo Gomez
* Kody Davis
* Felipe Rodriguez

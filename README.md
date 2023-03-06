# Mobile-Apps-Dice-Game-P1
Programmer: Samuel Jose
Professor: PhD John Baugh
School: University of Michigan Dearborn
Class: CIS 436 001 Mobile App Design and Implementation

Date Created:  02/28/2023
Last Modified: 03/04/2023

#Introduction
This is an implementation of a project design for a simple Math Game for kids. They roll a die (plural) from 1 to 6 and solve math equations. 
First to 20 points wins the game. 
The project was programmed in Android Studio IDE and was mainly programmed in Kotlin. the application is built to work for Android devices. 

#How to Play
The player set first on turn must roll the dice first before entering in a value for generated math equation to be guessed for points. 
The Dice Numbering System is as follows:
1. Addition         - 1 point
2. Subtraction      - 1 point
3. Multiplication   - 1 point
4. Double Points    - 2 points
5. Lose a Turn      - 0 points
6. Jackpot          - 5 points

The first player to 20 points wins the game.

#Features (As of 03/04/2023)
-Implemented a boolean like lock to enforce a turn based game. Player on his turn must roll first in order to answer. 
 The player can only roll once (reduces ability to go for an easier answer) and can only answer and get points for a correct answer once 
 (prevents spamming points on a correct answer.)



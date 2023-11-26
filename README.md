# Project Title

Mancala Game
A playable two person version of mancala using graphical user interface

# Description

Players take turns moving stones across the board attempting to gather the most stones in their own store. The player with the most stones in their store when the game is over wins the game. Players can load and save their own games and profiles.
### Dependencies

The scioer textbook needs to be installed before running my program, in order to use gradle
Java needs to be installed and added to path in order to run command to open gui window in another terminal

### Executing the Program

To run the program cd into GP4
Once inside folder, run gradle build to build the project.
Open another terminal and make sure java in installed and the right path is added
Run the executable to run the project.
If you wish to test the program with the junit testing use gradle test
Expected output should be a main menu with options for the user to use to play, save and load games.
Saving players happens automatically
Saving games you can choose where to save, ideally save into assets/games/

## Limitations

Everything should be working and the program should build and execute properly

## Author Information

Andrew Clare-Green
Email: aclaregr@uoguelph.ca
Student number: 1231678

## Development History
November 14 - set up repo and base of project
november 15 - fix pmd errors
november 17 - start working on serialization
november 18 - working serialization
november 19 - working on refactoring using the gameRules class and subclasses and mancaladatstructure
november 22 - starting gui
november 23 - working game on gui
november 24 - added menus saving games
november 25 - working save profiles and everything else up to date
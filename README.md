# Pebble Game - ECM2414 CA

## Description

This project is a Pebble game that uses multi-threading to simulate a game being played. The game starts with players taking ten pebbles, each at random from the three black bags in front of them, if the total weight in their hand is equal to 100 then they win. Otherwise, they discard a rock at random to a white bag and take another rock from a black bag until they hit the target. Black bags that are empty are then refilled by the corresponding white bag.

The program allows the user to input how many players they want to be playing and also the locations of the three files that contain lists of rock weights that the black bags will be filled by.

Threading was used as it was the best way to simulate multiple different people playing the game at the same time, the difficult part about this was imaking sure that all of the neccessary game methods we implemented were thread safe so that objects were not accessed by two or more threads simultaneously, and thus, breaking the game.

One of the challenges we faced was that with certain weights given in the files, it was not actually possible for any of the players to reach 100. In this case, we simply allowed the game to continue indefinitely.

## Running the Project

To run our project simply head over to the PebbleGame.java file and run it. You should be greeted with a message saying "Welcome to Pebble Game!!", along with information about what inputs you will need to make. You first input the number of players that you would like to play the game. This should be a non-negative integer, no text or other input will be accepted and it will continue to prompt you for a number until your input is valid.

Next it will ask for the locations for the first, second and third file locations that the program will read from to form the black bags. This must be the file path of your file, for example "inputFiles\exampleFile.csv".

Once both of these have been completed the program will run and you will get prompted with a message informing you of which player has won in the event that the target has been achieved.

Note - When giving input, entering "E" will exit out of the program.

## Testing the Project

To test the project firstly you need to run the code in visual studio and download the java extension 'Test Runner for Java'. 
The next step includes downloading the standalone version of junit (https://search.maven.org/search?q=g:org.junit.platform%20AND%20a:junit-platform-console-standalone) and adding it to either your own libs file or to a folder in the directory you will be running the tests from before navigating to the .vscode folder or by straight accessing the setting.json file, add the following code to the setting.json file replacing ENTER-PATH-HERE with the path of the file you downloaded earlier.

"java.project.referencedLibraries": [
        "lib/**/*.jar",
        "ENTER-PATH-HERE"
    ]

For further details see https://code.visualstudio.com/docs/java/java-testing .

Once this is installed an the path has been added to the settings file you just navigate to the test file that you would like to run and either press the run button to the side of the class line, or you can run a specific method test on its own. Equally by using the Testing section of the sidebar you can run all the tests symultaneously.

## Contributors

Jack Stallard - https://github.com/jckstllrd

Treeve White - https://github.com/TreeveWhite

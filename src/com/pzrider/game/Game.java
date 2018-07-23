package com.pzrider.game;

/**
 * This class is the main class of the "Rescue Kittens" application. "Rescue
 * Kittens" is a very simple, text based adventure game. Users can walk around
 * some scenery. That's all. It should really be extended to make it more
 * interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 * 
 * @author Phil Zoller, Michael Kölling, and David J. Barnes
 * @version 2018.07.18 Added "look" command (6.14), "feed" command (6.15), list
 *          commands (6.16 & 6.18), prin room item (6.20)
 * 
 * @version 2018.07.09 Refactored rooms, per Ex 6.5, 6.6, 6.7, 6.8, 6.11
 */

public class Game
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        // Ex 6.11
        // Added cellar
        Room outside, drawingRoom, entry, studio, bathroom, hallway, library,
                bedroom, diningRoom, kitchen, cellar;

        // create the rooms
        outside = new Room("outside the entrance to Ms. Brody's apartment");
        drawingRoom = new Room("in the drawing room");
        entry = new Room("in the entry");
        studio = new Room("in the studio");
        bathroom = new Room("in the bathroom");
        hallway = new Room("in the main hallway");
        library = new Room("in the library");
        bedroom = new Room("in the bedroom");
        diningRoom = new Room("in the dining room");
        kitchen = new Room("in the kitchen");
        // Ex 6.11
        cellar = new Room("in the cellar");

        // initialise room exits
        // Ex 6.8
        // Refactor exit setting
        // outside.setExits(entry, null, null, null);
        // drawingRoom.setExits(null, entry, null, null);
        // entry.setExits(hallway, studio, outside, drawingRoom);
        // studio.setExits(library, null, null, entry);
        // bathroom.setExits(bedroom, hallway, null, null);
        // hallway.setExits(diningRoom, library, entry, bathroom);
        // library.setExits(null, null, studio, hallway);
        // bedroom.setExits(null, diningRoom, bathroom, null);
        // diningRoom.setExits(null, kitchen, hallway, bedroom);
        // kitchen.setExits(null, null, null, diningRoom);

        // outside.setExits(entry, null, null, null);
        outside.setExit("north", entry);
        // drawingRoom.setExits(null, entry, null, null);
        drawingRoom.setExit("east", entry);
        // entry.setExits(hallway, studio, outside, drawingRoom);
        entry.setExit("north", hallway);
        entry.setExit("east", studio);
        entry.setExit("south", outside);
        entry.setExit("west", drawingRoom);
        // studio.setExits(library, null, null, entry);
        studio.setExit("north", library);
        studio.setExit("west", entry);
        // bathroom.setExits(bedroom, hallway, null, null);
        bathroom.setExit("north", bedroom);
        bathroom.setExit("east", hallway);
        // hallway.setExits(diningRoom, library, entry, bathroom);
        hallway.setExit("north", diningRoom);
        hallway.setExit("east", library);
        hallway.setExit("south", entry);
        hallway.setExit("west", bathroom);
        // library.setExits(null, null, studio, hallway);
        library.setExit("south", studio);
        library.setExit("west", hallway);
        // bedroom.setExits(null, diningRoom, bathroom, null);
        bedroom.setExit("east", diningRoom);
        bedroom.setExit("south", bathroom);
        // diningRoom.setExits(null, kitchen, hallway, bedroom);
        diningRoom.setExit("east", kitchen);
        diningRoom.setExit("south", hallway);
        diningRoom.setExit("west", bedroom);
        // kitchen.setExits(null, null, null, diningRoom);
        kitchen.setExit("west", diningRoom);

        // Ex 6.11
        library.setExit("down", cellar);
        cellar.setExit("up", library);

        // Ex 6.20 Add items to some rooms.
        drawingRoom.setItem(new Item("kitty cage", 10));
        studio.setItem(new Item("kitty cage", 10));
        diningRoom.setItem(new Item("kitty cage", 10));
        cellar.setItem(new Item("kitty cage", 10));

        currentRoom = outside; // start game outside
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println(
                "World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        this.printLocationInfo();
        // Ex 6.5
        // Commented out and will call printLocationInfo
        // System.out.println("You are " + currentRoom.getDescription());
        // System.out.print("Exits: ");
        // if (currentRoom.northExit != null) {
        // System.out.print("north ");
        // }
        // if (currentRoom.eastExit != null) {
        // System.out.print("east ");
        // }
        // if (currentRoom.southExit != null) {
        // System.out.print("south ");
        // }
        // if (currentRoom.westExit != null) {
        // System.out.print("west ");
        // }
        // System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else
            if (commandWord.equals("go")) {
                goRoom(command);
            }
            // Ex 6.14 Add "look" command
            else
                if (commandWord.equals("look")) {
                    look();
                }
                // Ex 6.15
                else
                    if (commandWord.equals("feed")) {
                        feed();
                    } else
                        if (commandWord.equals("quit")) {
                            wantToQuit = quit(command);
                        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words. Ex 6.16 List commands
     * 
     * Ex 6.18 Implement parser to return a list of commands and let printHelp
     * handle printing 6.16 Call a method to print available commands 6.18 Get a
     * list of commands and print it
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        // Ex 6.16 Print a list of available commands
        // Ex 6.18 Get a list of commands and print it
        // parser.showCommands();
        System.out.println(parser.getCommandList());
    }

    /**
     * Try to go in one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Ex 6.5
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        // Use new method in Room class
        // if (direction.equals("north")) {
        // nextRoom = currentRoom.northExit;
        // }
        // if (direction.equals("east")) {
        // nextRoom = currentRoom.eastExit;
        // }
        // if (direction.equals("south")) {
        // nextRoom = currentRoom.southExit;
        // }
        // if (direction.equals("west")) {
        // nextRoom = currentRoom.westExit;
        // }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            this.printLocationInfo();
            // Ex 6.5
            // Commented out and will call printLocationInfo, per
            // System.out.println("You are " + currentRoom.getDescription());
            // System.out.print("Exits: ");
            // if (currentRoom.northExit != null) {
            // System.out.print("north ");
            // }
            // if (currentRoom.eastExit != null) {
            // System.out.print("east ");
            // }
            // if (currentRoom.southExit != null) {
            // System.out.print("south ");
            // }
            // if (currentRoom.westExit != null) {
            // System.out.print("west ");
            // }
            // System.out.println();
        }
    }

    /*
     * Ex 6.15 Added "feed" command Feed the cats in te current room.
     */
    private void feed()
    {
        System.out.println("You have fed the cats in this room.");
    }

    // implementations of user commands:
    /*
     * Ex 6.14 Add "look" command Reveal the contents of the room.
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Ex 6.7 Modify to call Room class method to diplay rooms.
     */
    private void printLocationInfo()
    {
        // Ex 6.11
        // System.out.print("Exits: ");
        // Ex 6.7
        // Call Room class method to display exists
        System.out.println("You are " + currentRoom.description + ".  "
                + currentRoom.getExitString() + ".");
        // Ex 6.20 Print room item
        if (!currentRoom.isEmpty()) {
            System.out.println("\tThere is a "
                    + currentRoom.getItem().getDescription() + ". weighing "
                    + currentRoom.getItem().getWeight() + ".");
        }
        // Ex 6.7
        // Comment out to document changes for Week 2 Lab
        // if (currentRoom.northExit != null) {
        // System.out.print("north ");
        // }
        // if (currentRoom.eastExit != null) {
        // System.out.print("east ");
        // }
        // if (currentRoom.southExit != null) {
        // System.out.print("south ");
        // }
        // if (currentRoom.westExit != null) {
        // System.out.print("west ");
        // }
        System.out.println();
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     * 
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true; // signal that we want to quit
        }
    }
}

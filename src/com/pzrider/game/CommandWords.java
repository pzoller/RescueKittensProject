package com.pzrider.game;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game. It is
 * used to recognise commands as they are typed in.
 *
 * @author Phil Zoller, Michael Kölling and David J. Barnes
 * 
 * @version 2018.07.14 Ex 6.14, 6.15 added "look" command (6.14), added "feed"
 *          command (6.15), print a list of commands (6.16, 6.18)
 * 
 *          Ex 6.18 Change showAll to getCommandList and return a list of valid
 *          commands
 * 
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    // Ex 6.14 Add "look" command
    // Ex 6.15 Add "feed" command
    private static final String[] validCommands = { "go", "quit", "help",
            "look", "feed" };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word.
     * 
     * @return true if a given string is a valid command, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for (int i = 0; i < validCommands.length; i++) {
            if (validCommands[i].equals(aString)) return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Ex 6.16 Print a list of commands Ex 6.18 Change showAll to return a list
     * of commands
     * 
     * Ex 6.18 Change showAll to getCommandList and return a list of valid
     * commands
     * 
     * @return - The list of valid commands
     */
    // public void showAll() {
    public String getCommandList()
    {
        StringBuilder builder = new StringBuilder();
        for (String command : validCommands) {
            // Ex 6.18 refactor showAll to return a list
            // System.out.print(command + " ");
            builder.append(command + " ");
        }

        // Ex 6.18 refactor showAll to return a list
        // System.out.println();
        return builder.toString();
    }
}

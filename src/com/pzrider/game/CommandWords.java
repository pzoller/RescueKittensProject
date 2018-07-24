package com.pzrider.game;

import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 * <p>
 * This class holds an enumeration of all command words known to the game. It is
 * used to recognise commands as they are typed in.
 *
 * @author Phil Zoller, Michael Kölling and David J. Barnes
 * @version 2018.07.14 Ex 6.14, 6.15 added "look" command (6.14), added "feed"
 * command (6.15), print a list of commands (6.16, 6.18)
 * <p>
 * Ex 6.18 Change showAll to getCommandList and return a list of valid
 * commands
 */

public class CommandWords {
    // a constant array that holds all valid command words
    // Ex 6.14 Add "look" command
    // Ex 6.15 Add "feed" command
    private HashMap<String, CommandWord> validCommands;


    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        validCommands = new HashMap<>();
        for (CommandWord command : CommandWord.values()) {
            if (command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Find the CommandWord associated with a command word.
     *
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     * if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord) {
        CommandWord command = validCommands.get(commandWord);
        if (command != null) {
            return command;
        } else {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * Check whether a given String is a valid command word.
     *
     * @return true if a given string is a valid command, false if it isn't.
     */
    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
    }

    public void showAll() {
        for (String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}

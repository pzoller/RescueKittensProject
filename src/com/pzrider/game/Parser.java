package com.pzrider.game;

import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 * <p>
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and tries
 * to interpret the line as a two word command. It returns the command as an
 * object of class Command.
 * <p>
 * The parser has a set of known command words. It checks user input against the
 * known commands, and if the input is not one of the known commands, it returns
 * a command object that is marked as an unknown command.
 *
 * @author Phil Zoller, Michael Kölling and David J. Barnes
 * @version 2018.07.17  Print all available commands (6.16, 6.18)
 */
public class Parser {
    private CommandWords commands; // holds all valid command words
    private Scanner reader; // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand() {
        String inputLine; // will hold the full input line
        String word1 = null;
        String word2 = null;

        System.out.print("> "); // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next(); // get first word
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next(); // get second word
                // note: we just ignore the rest of the input line.
            }
        }

        return new Command(commands.getCommandWord(word1), word2);
    }

    /**
     * Ex 6.16 Print all available commands
     * <p>
     * Ex 6.18 Refactor showCommands to return a list of commands
     */
    // public void showCommands()
    public void showCommandList() {
        // Ex 6.18 Refactor showCommands to return a list of commands
        // commands.showAll();
        commands.showAll();
    }
}

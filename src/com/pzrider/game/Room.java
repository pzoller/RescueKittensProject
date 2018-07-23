package com.pzrider.game;

import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via exits. The exits are labelled north, east, south, west.
 * For each direction, the room stores a reference to the neighboring room, or
 * null if there is no exit in that direction.
 * 
 * @author Phil Zoller, Michael Kölling, and David J. Barnes
 * @version 218.07.17 Added an item
 * 
 * @version 2018.07.09 Refactored rooms, per Ex 6.5, 6.6, 6.7, 6.8, 6.11
 */
public class Room
{
    public String description;

    // Ex 6.20 Add an item
    private Item item;

    // Ex 6.8
    // Change exits to HashMap key-value pairs.
    private HashMap<String, Room> exits;
    // public Room northExit;
    // public Room southExit;
    // public Room eastExit;
    // public Room westExit;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open court yard".
     * 
     * @param description The room's description.
     */
    public Room(String description)
    {
        this.setItem(new Item());
        this.description = description;
        // Ex 6.8
        // Change rooms to HashMap
        this.exits = new HashMap<String, Room>();
    }

    /**
     * Define the exits of this room. Every direction either leads to another
     * room or is null (no exit there).
     * 
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    // Ex 6.8
    // Replace with more dynamic exit
    // public void setExits(Room north, Room east, Room south, Room west)
    // {
    // if (north != null) {
    // exits.put("north", north);
    // }
    // if (east != null) {
    // exits.put("east", east);
    // }
    // if (south != null) {
    // exits.put("south", south);
    // }
    // if (west != null) {
    // exits.put("west", west);
    // }
    // }

    public void setExit(String direction, Room room)
    {
        exits.put(direction, room);
    }

    /**
     * Ex 6.5 Return the room that is reached if we go from this room in
     * direction "direction". If there is no room in that direction, return
     * null.
     * 
     * @param direction
     * @return
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    /**
     * #x 6.7 Returns a string displaying the existing exits for the room
     * 
     * @return
     */
    public String getExitString()
    {
        StringBuilder builder = new StringBuilder();

        // Ex 6.11
        // Refactor to use HasMap
        // if (getExit("north") != null) {
        // builder.append("north ");
        // }
        //
        // if (getExit("east") != null) {
        // builder.append("east ");
        // }
        //
        // if (getExit("south") != null) {
        // builder.append("south ");
        // }
        //
        // if (getExit("west") != null) {
        // builder.append("west ");
        // }
        builder.append("Exits: ");
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            builder.append(exit + " ");
        }

        return builder.toString().trim();
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Ex 6.11 Return along description of this room.
     * 
     * @return A description of the room, includig exits.
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Ex 6.20 get the item
     * 
     * @return the item
     */
    public Item getItem()
    {
        return item;
    }

    /**
     * Set the item
     * 
     * @param item the item to set
     */
    public void setItem(Item item)
    {
        this.item = item;
    }

    public boolean isEmpty()
    {
        return !(this.item != null && !this.item.getDescription().isEmpty());
    }
}

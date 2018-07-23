package com.pzrider.game;

/**
 * Ex 6.20 An item that can be carried.
 * 
 * @author Phil Zoller
 * @version 2018.07.17 Initial set up.
 */
public class Item
{
    private String description;
    private int weight;

    /**
     * Ex 6.20 Descriptive constructor
     * 
     * @param description
     * @param weight
     */
    public Item(String description, int weight)
    {
        this.description = description;
        this.weight = weight;
    }

    /**
     * Ex 6.20 Empty constructor
     */
    public Item()
    {
        this.description = "";
        this.weight = 0;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return the weight
     */
    int getWeight()
    {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    void setWeight(int weight)
    {
        this.weight = weight;
    }

}

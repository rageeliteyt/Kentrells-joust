package com.cctc.amatlock.test;

import java.awt.*;

public class Land extends CoreObject{
    /**
     * Creates the core object. All subclasses
     * will call this with super.
     * The super call to the Rectangle class.
     *  @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public Land(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void tick()
    {
        if(x < -1)
        {
            setVelX(5);
        }
        if(x + width > Reference.WIDTH )
        {
            setVelX(-5);
        }

        x += getVelX();
        y += getVelY();




    }

    @Override
    public void render(Graphics g)
    {

        g.setColor(color);
        g.fillRect(x,y,width,height);


    }
}

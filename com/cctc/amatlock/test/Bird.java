package com.cctc.amatlock.test;

import java.awt.*;

public class Bird extends CoreObject{


    private boolean jumping = false;
    private boolean falling = true;
    /**
     * Creates the core object. All subclasses
     * will call this with super.
     * The super call to the Rectangle class.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public Bird(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    private boolean onGround()
    {
        Land land1 = Screen.getInstance().land1;
        Land land2 = Screen.getInstance().land2;
        Land land3 = Screen.getInstance().land3;
        Land land4 = Screen.getInstance().land4;
        Land land5 = Screen.getInstance().land5;
        Land land6 = Screen.getInstance().land6;

        if(intersects(land1) || intersects(land2) || intersects(land3) || intersects(land4) || intersects(land5) || intersects(land6) )
        {
            setVelY(0);
            return true;
        }
        return false;
    }

//    private boolean isFalling()
//    {
//        if(onGround() == false)
//        {
//            falling = true;
//        }
//        return false;
//    }




    @Override
    public void tick()
    {
        x += velX;
        y += velY;

        if(jumping)
        {
            velY--;
            if(velY == -21)
            {
                jumping = false;
                falling = true;
            }
        }

        if(falling)
        {
            setVelY(3);
        }
        if(onGround() )
        {
            setVelY(0);
        }



    }

    @Override
    public void render(Graphics g)
    {

        g.setColor(color);
        g.fillRect(x,y,width,height);

    }
}

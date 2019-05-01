package com.cctc.amatlock.test;

import java.awt.*;

public class Bird extends CoreObject{


    public boolean jumping = false;
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
        for(int i = 0; i < Screen.getObjectCounter(); i++)
        {
            CoreObject obj = Screen.getCoreObjects()[i];
            if( obj.intersects(this))
            {
                setVelY(0);
                return true;
            }
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
        if(jumping)
        {
            y -= 10;
            setVelY(-10);
            jumping = false;
        }

        if(onGround() && !jumping )
        {
            falling = false;
            setVelY(0);
        }
        else
        {
            velY += 0.997;
        }


        x += velX;
        y += velY;


    }

    @Override
    public void render(Graphics g)
    {

        g.setColor(color);
        g.fillRect(x,y,width,height);

    }
}

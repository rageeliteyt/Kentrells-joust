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

            Rectangle top = new Rectangle(obj.x, obj.y, obj.width, 1);
            Rectangle bottom = new Rectangle(obj.x, obj.y + obj.height, obj.width, 1);

            if( top.intersects(this))
            {
                setVelY(0);
                y = top.y - height;
                return true;
            }

            if( bottom.intersects(this))
            {
                setVelY(2);
            }
        }

        return false;
    }


    @Override
    public void tick()
    {
        if(jumping)
        {
            setVelY(-15);
            jumping = false;
        }
        else if(onGround())
        {
            falling = false;
            setVelY(0);
        }
        else
        {
            velY += 0.697;
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

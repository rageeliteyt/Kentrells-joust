package com.cctc.amatlock.test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener
{
    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

        Bird player = Screen.getInstance().player;

        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            player.setVelX(-5);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            player.setVelX(5);
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            player.jumping = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

        Bird player = Screen.getInstance().player;
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            player.setVelX(0);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            player.setVelX(0);
        }

    }
}

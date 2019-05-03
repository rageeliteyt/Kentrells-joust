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
        Bird player2 = Screen.getInstance().player2;

        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            player.setVelX(-5);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            player.setVelX(5);
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            player.jumping = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_A)
        {
            player2.setVelX(-5);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D)
        {
            player2.setVelX(5);
        }
        else if(e.getKeyCode() == KeyEvent.VK_W)
        {
            player2.jumping = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

        Bird player = Screen.getInstance().player;
        Bird player2 = Screen.getInstance().player2;

        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            player.setVelX(0);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            player.setVelX(0);
        }


        if(e.getKeyCode() == KeyEvent.VK_A)
        {
            player2.setVelX(0);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D)
        {
            player2.setVelX(0);
        }

    }
}

package com.cctc.amatlock.test;

import com.cctc.amatlock.test.utilities.Images;
import com.cctc.amatlock.test.utilities.ResourceLoader;
import com.cctc.amatlock.test.utilities.Sounds;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Screen extends Canvas implements Runnable
{
    private static final long serialVersionUID = -1890564841829395437L;

    private static JFrame frame = new JFrame();  // This is the window object
    private static Screen screen = new Screen();  // Our program
    private static CoreObject[] coreObjects = new CoreObject[25];
    private static int objectCounter = 0;

    // Instance variables
    private boolean running = false;  // Boolean flipped when the program starts or stops.
    private Thread thread;  // Don't worry about what this is.

    public Bird player;
    public Land land1;
    public Land land2;

    public Land land4;
    public Land land5;
    public Land land6;
    public Land land7;
    public Bird player2;



    public static Screen getInstance()
    {
        return screen;
    }
    public static CoreObject[] getCoreObjects()
    {
        return coreObjects;
    }
    public static int getObjectCounter()
    {
        return objectCounter;
    }
    public static void addObject(CoreObject object)
    {
        coreObjects[objectCounter] = object;
        objectCounter++;
    }

    /**
     * Used to draw the backdrop for our program.
     * @param g graphics engine used to draw 2d in window.
     */
    public void drawBackground(Graphics g) {

        // Making a dark gray background.
        // First set the draw color to dark grey.
        g.setColor(Color.WHITE);

        // Next make a rectangle starting in the top right corner (first 2 parameters)
        // Make it the width and height of the window (last 2 parameters)
        g.fillRect(0, 0, Reference.WIDTH, Reference.HEIGHT);

        g.drawImage(Images.background, 0, 0, null);
    }
    public void drawForeground(Graphics g)
    {
        for(int i = 0; i < objectCounter; i++)
        {
            CoreObject obj = coreObjects[i];
            obj.render(g);
        }


        player.render(g);

        player2.render(g);
    }

    public void render()
    {
        // Buffer strategy and graphics are used to draw the pixels on the screen.
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null)
        {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics(); // This is what is used to draw pixels on our screen.

        // Draw here.
        drawBackground(g);
        drawForeground(g);

        g.dispose();  // Disposes our graphics context (if we did not do this, animations would not work properly, it would also eat up a lot of memory
        bs.show();  // Shows whatever graphics were just disposed of
    }

    public void tick()
    {
        for(int i = 0; i < objectCounter; i++)
        {
            CoreObject obj = coreObjects[i];
            obj.tick();
        }

        if(player.intersects(player2))
        {
            double p1VelX = player.velX;
            player.velX += player2.velX * 2;
            player2.velX += p1VelX * 2;
        }






        player.tick();
        player2.tick();
    }

    /**
     * Does the things needed when our program starts.
     */
    public void init()
    {
        ResourceLoader.loadImages(); // loads images from files.
        ResourceLoader.loadSounds();

        Sounds.background.start();
        Sounds.background.loop(Clip.LOOP_CONTINUOUSLY);

        land1 = new Land(111,Reference.CENTER_Y - 111,Reference.WIDTH/4, 25, Color.RED );
       land2 = new Land(222,Reference.CENTER_Y - 222,Reference.WIDTH/4,25 ,Color.BLUE );


       land4 = new Land(333,Reference.CENTER_Y - - 155,Reference.WIDTH/4,25,Color.WHITE);
       land5 = new Land(222,Reference.CENTER_Y - - 222 ,Reference.WIDTH,25 ,Color.WHITE);


       land6 = new Land(111,Reference.CENTER_Y - - 111,Reference.WIDTH/4 ,25 ,Color.CYAN);


        land7 = new Land(150,Reference.CENTER_Y - 0,Reference.CENTER_X ,25 ,Color.BLUE );

        addObject(land1);
        addObject(land2);

        addObject(land4);
        addObject(land5);
        addObject(land6);
        addObject(land7);

        player = new Bird(275,210,30,30,Color.getHSBColor( 57,9,16 ) );

       player2 = new Bird(Reference.CENTER_X - 150,0,40,40,Color.BLACK);

        land6.setVelX(-1);



       KeyInput keyInput = new KeyInput();
        this.addKeyListener(keyInput);
    }



    @Override
    /**
     * This run method is what runs the program.
     * We use a while true loop to repeatedly draw our screen.
     */
    public void run()
    {
        init(); //Initializes our program

        // This code has to do with making sure our program only updates so many times a second.

        long lastTime = System.nanoTime();  // Get the time when the program starts.
        final double numTicks = 60.0;  // This is the number of times per second we want to tick. (fps)
        double n = 1000000000 / numTicks;
        double delta = 0;   // Number of nanoseconds since last tick.
        int frames = 0;     // Counts the frames per second.
        int ticks = 0;      // Number of ticks per second.
        long timer = System.currentTimeMillis();  // Time in milliseconds

        while (running)
        {
            long currentTime = System.nanoTime();   // Current time in milliseconds
            delta += (currentTime - lastTime) / n;  // Add time passed since the last frame.
            lastTime = currentTime;  // Update "lastTime" for when the loop reruns.

            if (delta >= 1)
            {
                // Has it been long enough to update
                tick();  // Used to update things between frames
                ticks++;  // Increment ticks
                delta--; // Reset the delta
            }

            render();  // Renders the screen
            frames++;  // Increment the number of frames

            if (System.currentTimeMillis() - timer > 1000)
            {
                // Used to print the frames and ticks per second
                timer += 1000;
                System.out.println(ticks + " Ticks, FPS: " + frames);  // Prints the TPS and FPS to console
                frame.setTitle(Reference.TITLE + "        Ticks: " + ticks + "    FPS: " + frames);   // Adds frames and ticks to window title.
                ticks = 0;  // Reset ticks and frames
                frames = 0;
            }
        }
        stop(); // Once exit the loop, stop the program
    }

    public static void main(String args[])
    {
        frame.add(screen);  // Adds our program as a component to the frame
        frame.setTitle(Reference.TITLE);
        frame.setSize(Reference.WIDTH + 7, Reference.HEIGHT + 30); // Size of our window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit the program with X
        frame.setFocusable(true); // Allows click and input
        frame.setLocationRelativeTo(null); // Starts the window in the middle of the screen
        frame.setResizable(false); // Allowing resizing will complicate things more.
        frame.setVisible(true); // This shows the frame/window
        frame.pack();
        screen.start(); // Starts the program
    }

    private synchronized void start() {
        if (running) // If program is running, we do not want to run the program again
            return;
        else
            running = true;
        thread = new Thread(this);  // thread that controls our program
        thread.start();  // starts the thread, thus our program
    }

    private synchronized void stop() {
        if (!running)  // If the program has stopped, why stop it again?
            return;
        else
            running = false;

        try {
            thread.join();  // Convenient way to close thread.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(1);  // exits program
    }
}
package com.cctc.amatlock.test.utilities;

import javax.sound.sampled.*;
import java.io.File;
import java.io.InputStream;

public class SoundLoader
{
    private static final String RESOURCES = "./resources/";
    private static final String SOUNDS = RESOURCES + "sounds/";

    private InputStream sound;

    public Clip loadSound(String path) throws Exception
    {
        File yourFile;
        AudioInputStream stream;
        AudioFormat format;
        Line.Info info;
        Clip clip;


        yourFile = new File(SOUNDS + path);
        stream = AudioSystem.getAudioInputStream(yourFile);
        format = stream.getFormat();
        info = new DataLine.Info(Clip.class, format);
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);

        return clip;

    }





}

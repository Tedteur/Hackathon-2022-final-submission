package com.example.calculator_real_;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.ArrayList;
import java.util.function.LongToIntFunction;

public class VolumeController {
    private int upperLimit;
    private int lowerLimit;
    private Clip clip;
    VolumeController(int upperLimit, int lowerLimit, Clip clip){
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.clip = clip;
    }
    public int getUpperLimit (){
        return upperLimit;
    }
    public int getLowerLimit (){
        return lowerLimit;
    }
    public void setUpperLimit(int upperLimit){
        this.upperLimit = upperLimit;
    }
    public void setLowerLimit(int lowerLimit){
        this.lowerLimit = lowerLimit;
    }
    public float getVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void setVolume(float volume) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
    public void controlVolume(byte[] array) {
        double time = 0;
        double durationInSeconds = (clip.getMicrosecondLength() * 1000000);
        while (time < durationInSeconds){
            int volume = array[(int)(Math.round(time/0.05))];
            System.out.println(time);
            System.out.println(volume);
            if (volume < lowerLimit){
                setVolume(lowerLimit/volume * 0.5f);
                System.out.println(lowerLimit/volume * 0.5f);
            }
            else if (volume > upperLimit){
                setVolume(volume/upperLimit * 0.5f);
                System.out.println(upperLimit/volume * 0.5f);
            }
            time = time + 0.05;

            try{
                Thread.sleep(50);
            } catch (InterruptedException e){
                System.out.println(e.getStackTrace());
            }

            // Creating an object of Path class and
            // assigning local directory path of file to it



        }
    }
}

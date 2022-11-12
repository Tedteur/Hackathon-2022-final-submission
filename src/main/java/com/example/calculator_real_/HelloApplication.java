package com.example.calculator_real_;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 830, 540);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static Mixer mixer;
    public static Clip clip;
    public static VolumeController controller;
    public static void main(String[] args) {
        launch();
        Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
        mixer = AudioSystem.getMixer(mixInfos[4]);
        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
        try {
            clip = (Clip) mixer.getLine(dataInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            URL soundURL = HelloApplication.class.getResource("videoplayback.wav");
            AudioInputStream audiostream = AudioSystem.getAudioInputStream(soundURL);
            audiostream.getFormat().getFrameSize();
            clip.open(audiostream);
        } catch (LineUnavailableException lue) {
            lue.printStackTrace();
        } catch (UnsupportedAudioFileException uafe) {
            uafe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        File file = new File("videoplayback.wav");
        System.out.print(file.exists());
        try {
            FileInputStream fl = new FileInputStream(file);
            byte[] arr = new byte[(int) file.length()];
            fl.read(arr);
            fl.close();
            controller = new VolumeController(127, -128, clip);
            clip.start();
            controller.controlVolume(arr);
            do {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            } while (clip.isActive());
        } catch (IOException e) {
            System.out.println(e.getStackTrace());}
    }
}

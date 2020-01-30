package com.pioterDeveloper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Downloader {

    public static void urlToImage(String url, String fileName, String fileType) throws IOException {

        URL website = new URL(url);

        if(website.openConnection() != null) {
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());

            String path =  System.getProperty("user.dir") + "/downloads/";
            String file_name = fileName + fileType;

            File directory = new File(path);
            if (! directory.exists()){
                directory.mkdir();
            }

            File file = new File(path + "/" + file_name);
            try{
                FileOutputStream fos = new FileOutputStream(file);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
            catch (IOException e){
                e.printStackTrace();
                System.exit(-1);
            }

        }
        else{
            System.out.println("Connection error: " + url);
        }

    }

    public static void imageDownloader(ArrayList<Content> content){

        try{

            for(Content c: content) {


                URL tmp = new URL(c.img_url);
                HttpURLConnection huc = (HttpURLConnection) tmp.openConnection();
                int responseCode = huc.getResponseCode();

                if(responseCode == 200){
                    urlToImage(c.img_url, c.title, c.fileType);
                    System.out.println("Response code: 200 - OK " + "- File: " + "\"" + c.title + "\"");
                }
                else
                {
                    System.out.println("Response code: " + responseCode + " - " + huc.getResponseMessage() + " - File: " + "\"" + c.title + "\"");
                }

            }
        }
        catch(IOException e){

            e.printStackTrace();
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }

}

package com.pioterDeveloper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.pioterDeveloper.Downloader.imageDownloader;

public class Main {

    public static void main(String[] args) {

        WebToFile webToFile = new WebToFile();
        ContentFinder finder = new ContentFinder();
        ArrayList<Content> content = new ArrayList<Content>();
        Scanner input = new Scanner(System.in);

        String address = "";
        String folder = "";

        System.out.println("Paste Web Page address: ");
        address = input.nextLine();

        webToFile.setWebPageAddress(address);

        try{
            webToFile.webpageToFile();

        }catch(IOException e){

            e.printStackTrace();
        }

        finder.setDomainAddress(webToFile.getAddress());
        finder.setHtmlName(webToFile.getHtmlName());
        finder.findImage();

        content = finder.getImageBase();

        System.out.println("\nPlease Wait!!!\n");

        imageDownloader(content);

        System.out.println("\nDownload is complete!!!\n");

        System.out.println("Press any key to exit...");
        input.nextLine();
    }
}

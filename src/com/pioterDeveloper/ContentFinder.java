package com.pioterDeveloper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentFinder {
    private ArrayList<Content> imageBase = new ArrayList<Content>();
    //Pattern title_regex = Pattern.compile("title=\"[a-zA-Z0-9\\s]+");
    //Pattern img_url_regex = Pattern.compile("(http|https):\\/\\/([a-zA-Z0-9]+.[a-z]+|www.[a-zA-Z0-9]+.[a-z]+)\\/(?:[:a-zA-Z0-9\\s\\/.:_-]*)[.]png|jpg|svg|gif|jpeg|swf");
    private String webPage;
    private String domainAddress;
    private String htmlName;
    private OpenFile open = new OpenFile();

    public void setDomainAddress(String address){
        this.domainAddress = address;
    }

    public  void  setHtmlName(String fileName){
        this.htmlName = fileName;
    }

    private void loadPageContent(){
        open.openFIle( this.htmlName);
        this.webPage = open.getContent();
    }
    public void findImage(){

        loadPageContent();
        Matcher title_matcher = Pattern.compile("title=\\\"[a-zA-Z0-9\\s-\"]+>").matcher(webPage);
        Matcher url_matcher = Pattern.compile("(?:[:a-zA-Z0-9\\s\\/.:_-]*)[.](png|jpg|svg|gif|jpeg|swf)").matcher(webPage);
        //(http|https):\/\/([a-zA-Z0-9]+.[a-z]+|www.[a-zA-Z0-9]+.[a-z]+)\/(?:[:a-zA-Z0-9\s\/.:_-]*)[.](png|jpg|svg|gif|jpeg|swf)
        //((http|https):\/\/([a-zA-Z0-9]+.[a-z]+|www.[a-zA-Z0-9]+.[a-z]+)\/(?:[:a-zA-Z0-9\s\/.:_-]*)[.](png|jpg|svg|gif|jpeg|swf))|\/\/.*(\.(png|jpg|svg|gif|jpeg|swf))

        int it = 0;
        while(url_matcher.find()) {

            Content temp = new Content();

            if(title_matcher.find()){
                temp.setTitle(title_matcher.group(0).replaceAll("title=\"", "").replaceAll("\">",""));
            }
            else{
                temp.setTitle(""+it);
            }

            temp.setImg_url(url_matcher.group(0), this.domainAddress);

            imageBase.add(temp);
            it++;
        }
    }



    public ArrayList<Content> getImageBase() {
        return imageBase;
    }
}

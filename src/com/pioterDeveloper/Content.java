package com.pioterDeveloper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Content {

    protected String url;
    protected String title;
    protected String img_url;
    protected String kindOfProtocol;
    protected String fileType;

    Content(){}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url, String domain_url) {
        setFileType(img_url);
        if(findProtocol(img_url) == true){
            this.img_url = img_url;
        }
        else if(findSlashes(img_url) == true){
            this.img_url = "http:" + img_url;
        }
        else{
            this.img_url = domain_url + img_url;
        }

    }

    protected boolean findProtocol(String content){
        Matcher title_matcher = Pattern.compile("http").matcher(content);

        if(title_matcher.find()){
            this.kindOfProtocol = "http";
            return true;
        }
        else{
            this.kindOfProtocol = "";
            return false;
        }
    }

    protected boolean findSlashes(String content){
        Matcher slash_finder = Pattern.compile("\\/{2}").matcher(content);

        if(slash_finder.find()){
            return true;
        }
        else{
            return false;
        }
    }

    private void setFileType(String img_url){
       Matcher fileType_Matcher = Pattern.compile("png|jpg|svg|gif|jpeg|swf").matcher(img_url);
       if(fileType_Matcher.find()){
           this.fileType = "." + fileType_Matcher.group(0);
       }
       else{
           this.fileType = ".jpg";
       }
    }

    public String getKindOfProtocol() {
        return kindOfProtocol;
    }
}

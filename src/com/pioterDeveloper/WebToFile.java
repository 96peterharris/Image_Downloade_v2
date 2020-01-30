package com.pioterDeveloper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebToFile {
    private String webPageAddress;
    private String domainAddress;
    private String htmlName;
    WebToFile(){}

    public void setWebPageAddress(String address){
        this.webPageAddress = address;
    }

    public String getAddress(){
        return this.webPageAddress;
    }

    public String getHtmlName() {
        return htmlName;
    }

    public void setHtmlName(String htmlName) {
        this.htmlName = htmlName;
    }

    private void domainMatcher(){
        Matcher domain_matcher = Pattern.compile("http.*\\.[a-z]+\\/").matcher(this.webPageAddress);

        if(domain_matcher.find()){
            this.domainAddress = domain_matcher.group(0);
        }

    }

    private void createFileName(){
        Matcher htmlName_matcher = Pattern.compile("\\/\\/.*\\.[a-z]+\\/").matcher(this.domainAddress);

        if(htmlName_matcher.find()){
            String tmp1 = htmlName_matcher.group(0).replaceAll("\\.","_");;
           // System.out.println(tmp1);
            String tmp2 = tmp1.replaceAll("\\/+","");
            //System.out.println(tmp2);

            this.htmlName = tmp2 + ".html";
        }
    }

    public void webpageToFile() throws IOException {

        domainMatcher();
        createFileName();
        URL website = new URL(this.webPageAddress);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(this.htmlName);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }
}

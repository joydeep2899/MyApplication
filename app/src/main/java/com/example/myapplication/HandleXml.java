package com.example.myapplication;
import android.os.AsyncTask;

import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
public class HandleXml  {
List<String> titles,links,descriptions,urlstrings;
   String  title,link,description,urlstring=null;
    List<String> Category;
XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;
    HandleXml(String url){
        this.urlstring=url;
    }

    public List<String> getTitle(){

        return  titles;


    }

    public List<String> getDescription(){

        return  descriptions;


    }
    public List<String> getlink(){

        return  links;


    }
      public void parseXml(XmlPullParser xmlPullParser){
int event;

String text=null;
String name=xmlPullParser.getName();
try{
    event=xmlPullParser.getEventType();
    while (event != xmlPullParser.END_DOCUMENT) {

        switch (event) {

            case XmlPullParser.START_DOCUMENT:
                break;
            case XmlPullParser.TEXT:

                text = xmlPullParser.getText();
                break;

            case XmlPullParser.END_TAG:

                if (name.equals("title")) {
                    titles.add(text);

                    break;


                } else if (name.equals("link")) {

                    links.add(text);
                    break;
                } else if (name.equals("description")) {

                    descriptions.add(text);
                    break;
                } else {
                }
                break;

        }


        event = xmlPullParser.next();
    }

    parsingComplete=false;

}catch (Exception e){
e.printStackTrace();
}


      }


public void fetchXml() {
   // AsyncTask<URL,Integer,Long>;
   Thread thread=new Thread(new Runnable() {
       @Override
       public void run() {

           try{
               URL url=new URL(urlstring);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
               conn.setRequestMethod("GET");
      conn.connect();

               InputStream inputStream=conn.getInputStream();
               xmlFactoryObject=XmlPullParserFactory.newInstance();
               XmlPullParser xmlPullParser=xmlFactoryObject.newPullParser();

               xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);

               xmlPullParser.setInput(inputStream,null);

               parseXml(xmlPullParser);
               inputStream.close();
           }catch (Exception exception){

           }



       }
   });

   thread.start();

}





}

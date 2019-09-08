package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.widget.ListView;
import android.widget.Toast;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.ViewModelProvider;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class Main2Activity extends AppCompatActivity implements LifecycleOwner {
RecyclerView recyclerView;
RecyclerView.Adapter adapter;
    List<RssViewModel> FeedModelList;

    MyAdapter mAdapter;


    final Context local = this;
String dataset[]=new String[3];
String titles,descriptions,links;
    public String url="https://in.ign.com/feed.xml";
    List<String> article[];

    int i;

HandleXml obj;
RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dataset[0] = "hello ";
        dataset[1] = "joydeep";
        dataset[2] = "test";

        // List<Article> articles;

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        new FetchFeedTask().execute((Void) null);



    }





    private class FetchFeedTask extends AsyncTask<Void, Void, Boolean> {

        private String urlLink;

        @Override
        protected void onPreExecute() {

            urlLink =url;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            if (TextUtils.isEmpty(urlLink))
                return false;

            try {
                if(!urlLink.startsWith("http://") && !urlLink.startsWith("https://"))
                    urlLink = "http://" + urlLink;

                URL url = new URL(urlLink);
                InputStream inputStream = url.openConnection().getInputStream();
                FeedModelList = parseFeed(inputStream);
                return true;
            } catch (IOException e) {
                Log.e("e", "Error", e);
            } catch (XmlPullParserException e) {
                Log.e("e", "Error", e);
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {


            if (success) {
                //mFeedTitleTextView.setText("Feed Title: " + mFeedTitle);
                //mFeedDescriptionTextView.setText("Feed Description: " + mFeedDescription);
               // mFeedLinkTextView.setText("Feed Link: " + mFeedLink);
                // 1Fill RecyclerView

                recyclerView.setAdapter(new MyAdapter(FeedModelList));
            } else {
                Toast.makeText(Main2Activity.this,
                        "Enter a valid Rss feed url",
                        Toast.LENGTH_LONG).show();
            }
        }

        public List<RssViewModel> parseFeed(InputStream inputStream) throws XmlPullParserException,
                IOException {
            String title = null;
            String link = null;
            String description = null;
            boolean isItem = false;
            List<RssViewModel> items = new ArrayList<>();

            try {
                XmlPullParser xmlPullParser = Xml.newPullParser();
                xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                xmlPullParser.setInput(inputStream, null);

                xmlPullParser.nextTag();
                while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                    int eventType = xmlPullParser.getEventType();

                    String name = xmlPullParser.getName();
                    if(name == null)
                        continue;

                    if(eventType == XmlPullParser.END_TAG) {
                        if(name.equalsIgnoreCase("item")) {
                            isItem = false;
                        }
                        continue;
                    }

                    if (eventType == XmlPullParser.START_TAG) {
                        if(name.equalsIgnoreCase("item")) {
                            isItem = true;
                            continue;
                        }
                    }

                    Log.d("MyXmlParser", "Parsing name ==> " + name);
                    String result = "";
                    if (xmlPullParser.next() == XmlPullParser.TEXT) {
                        result = xmlPullParser.getText();
                        xmlPullParser.nextTag();
                    }

                    if (name.equalsIgnoreCase("title")) {
                        title = result;
                    } else if (name.equalsIgnoreCase("link")) {
                        link = result;
                    } else if (name.equalsIgnoreCase("description")) {
                        description = result;
                    }

                    if (title != null && link != null && description != null) {
                        if(isItem) {
                            RssViewModel item = new RssViewModel(title, link, description);
                            items.add(item);
                        }
                        else {
                            titles = title;
                           links = link;
                            descriptions = description;
                        }

                        title = null;
                        link = null;
                        description = null;
                        isItem = false;
                    }
                }

                return items;
            } finally {
                inputStream.close();
            }
        }

    }












      //-  while(obj.parsingComplete);



}

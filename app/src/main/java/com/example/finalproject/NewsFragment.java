package com.example.finalproject;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private ListView newsListView;
    private ProgressBar progressBar;
    private List<NewsArticle> newsArticleList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    MyOpener myOpener;
    SQLiteDatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        newsListView = view.findViewById(R.id.newsListView);
        progressBar = view.findViewById(R.id.progressBar);
        myOpener = new MyOpener(getContext());

        newsAdapter = new NewsAdapter();
        newsListView.setAdapter(newsAdapter);

        NewsArticle article = new NewsArticle("Power Ranger", "this is the power ranger description", "", "", "");
        newsArticleList.add(article);
        newsAdapter.notifyDataSetChanged();

        new NewsList().execute();

        //click for items in the list
        newsListView.setOnItemClickListener( (parent, itemView, position, id) -> {

        });
        // Inflate the layout for this fragment
        return view;
    }

    private class NewsList extends AsyncTask<String, Integer, List<NewsArticle>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            newsListView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<NewsArticle> doInBackground(String... strings) {
            List<NewsArticle> articles = new ArrayList<>();
            try {
                URL url = new URL("https://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream response = urlConnection.getInputStream();
                Log.d("AsyncResponse", "doInBackground: " + response);

                //response is XML so we use XMLpullparser
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(response, "UTF-8");

                int eventType = parser.getEventType();
                NewsArticle currentArticle = null;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String tagName = parser.getName();
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if (tagName.equalsIgnoreCase("item")) {
                                currentArticle = new NewsArticle();
                            } else if (currentArticle != null) {
                                if (tagName.equalsIgnoreCase("title")) {
                                    currentArticle.setTitle(parser.nextText());
                                } else if (tagName.equalsIgnoreCase("description")) {
                                    currentArticle.setDescription(parser.nextText());
                                } else if (tagName.equalsIgnoreCase("link")) {
                                    currentArticle.setLink(parser.nextText());
                                } else if (tagName.equalsIgnoreCase("pubDate")) {
                                    currentArticle.setPubDate(parser.nextText());
                                } else if (tagName.equalsIgnoreCase("media:thumbnail")) {
                                    currentArticle.setThumbnail(parser.getAttributeValue(null, "url"));
                                }
                            }
                            break;

                        case XmlPullParser.END_TAG:
                            if (tagName.equalsIgnoreCase("item") && currentArticle != null) {
                                articles.add(currentArticle);
                            }
                            break;
                    }
                    eventType = parser.next();
                }
                response.close();
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return articles;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<NewsArticle> articles) {
            super.onPostExecute(articles);
            newsArticleList.clear();
            newsArticleList.addAll(articles);
            newsAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
            newsListView.setVisibility(View.VISIBLE);
        }
    }

    private class NewsAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return newsArticleList.size();
        }
        @Override
        public Object getItem(int position) {
            return newsArticleList.get(position);
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_news, parent, false);
            }
            NewsArticle newsArticle = (NewsArticle) getItem(position);

            TextView titleView = convertView.findViewById(R.id.title_textView);
            ImageView imageView = convertView.findViewById(R.id.news_ImageView);
            ImageButton button = convertView.findViewById(R.id.saveButton);

            titleView.setText(newsArticle.getTitle());
            if (newsArticle.getThumbnail() != null && !newsArticle.getThumbnail().isEmpty()) {
                Picasso.get()
                        .load(newsArticle.getThumbnail())
                        .placeholder(R.drawable.ic_launcher_background) // Optional placeholder image
                        .error(R.drawable.ic_launcher_background) // Optional error image
                        .into(imageView);
            } else {
                imageView.setImageResource(R.drawable.ic_launcher_background); // Set a default image if thumbnail is missing
            }
            return convertView;
        }
    }
}
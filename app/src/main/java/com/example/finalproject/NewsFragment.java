package com.example.finalproject;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    private ListView newsListView;
    private ProgressBar progressBar;
    private ArrayList<NewsArticle> newsArticleList = new ArrayList<>();
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
        // Inflate the layout for this fragment
        return view;
    }

    private class NewsList extends AsyncTask<String, Integer, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
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
            return convertView;
        }
    }
}
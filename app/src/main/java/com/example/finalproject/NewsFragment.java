package com.example.finalproject;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

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
        newsAdapter = new NewsAdapter(getContext(), newsArticleList);
        newsListView.setAdapter(newsAdapter);
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
            return 0;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
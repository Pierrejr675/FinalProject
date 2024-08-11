package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String thumbnail = intent.getStringExtra("thumbnail");
        String pubdate = intent.getStringExtra("pubDate");
        String link = intent.getStringExtra("link");

        TextView titleTextView = findViewById(R.id.newTitle_TextView);
        ImageView thumbnailImageView = findViewById(R.id.newThumbnail_ImageView);
        TextView descriptionTextView = findViewById(R.id.newDescription_TextView);
        TextView pubdateTextView = findViewById(R.id.newDate_TextView);
        Button linkButton = findViewById(R.id.newLink_Button);

        titleTextView.setText(title);
        if (thumbnail != null && !thumbnail.equals("")) {
            Picasso.get()
                    .load(thumbnail)
                    .placeholder(R.drawable.ic_launcher_background) // Optional placeholder image
                    .error(R.drawable.ic_launcher_background) // Optional error image
                    .into(thumbnailImageView);
        } else {
            thumbnailImageView.setImageResource(R.drawable.ic_launcher_background); // Set a default image if thumbnail is missing
        }
        descriptionTextView.setText(description);
        pubdateTextView.setText(pubdate);
        linkButton.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(browserIntent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
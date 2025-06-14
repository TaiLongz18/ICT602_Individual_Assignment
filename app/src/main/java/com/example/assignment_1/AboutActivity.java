package com.example.assignment_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AboutActivity extends AppCompatActivity {

    TextView textViewUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        textViewUrl = findViewById(R.id.textViewUrl);
        textViewUrl.setOnClickListener(view -> {
            // Replace this URL with your actual GitHub repo
            String url = "https://github.com/TaiLongz18/ICT602_Individual_Assignment";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }
}

package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RatingBar;

public class MainActivity2 extends AppCompatActivity {

    Book book;

    RatingBar ratingBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         WebView webView = findViewById(R.id.web);

         webView.getSettings().setJavaScriptEnabled(true);

         WebClient webViewClient = new WebClient();

         webView.setWebViewClient(webViewClient);

         ratingBook = findViewById(R.id.rating);

         Bundle arguments = getIntent().getExtras();

         if (arguments != null) {
             book = (Book) arguments.getSerializable(Book.class.getSimpleName());

             ratingBook.setRating(book.getRating());

             book.nameBook = book.nameBook.replace(" ","_");
             String url = String.format("https://ru.wikipedia.org/wiki/%s", book.nameBook);
             webView.loadUrl(url);
         }

    }

    @Override
    public void onBackPressed() {
        book.setRating(ratingBook.getRating());

        Intent intent = new Intent();

        intent.putExtra(Book.class.getSimpleName(),book);
        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }

    public class WebClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url){
            return false;
        }

    }
}
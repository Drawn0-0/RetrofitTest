package com.example.acer.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvMain = (TextView) findViewById(R.id.tv_main);
        String url = "https://api.douban.com/v2/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Test myTest = retrofit.create(Test.class);
        Call<BookSearchResponse> searchBooks = myTest.getSearchBooks("小王子", "", 0, 3);
        searchBooks.enqueue(new Callback<BookSearchResponse>() {
            @Override
            public void onResponse(Call<BookSearchResponse> call, Response<BookSearchResponse> response) {
                int total = response.body().getTotal();
                System.out.println("总共："+total);
                System.out.println(response.body().getBooks().get(0).getAuthor_intro());
                tvMain.setText(response.body().getBooks().get(1).getAuthor_intro());
            }

            @Override
            public void onFailure(Call<BookSearchResponse> call, Throwable t) {

            }
        });
//        System.out.println(bookSearchResponse.getCount());
    }

}

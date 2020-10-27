package com.example.handlingapiusingretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView textView, searchBox;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textviewdata);
        searchBox = findViewById(R.id.searchbox);
        searchBtn = findViewById(R.id.searchbtn);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://powerful-oasis-35253.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            Call<List<Post>> call;

            @Override
            public void onClick(View v) {
                if(searchBox.getText().length()==0)
                {
                    call = jsonPlaceHolderApi.getPosts();
                }
                else {
                    call = jsonPlaceHolderApi.getPostsQueryBarcode(searchBox.getText().toString());
                }
                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if(!response.isSuccessful())
                        {
                            textView.setText(response.code());
                            return;
                        }
                        List<Post> posts = response.body();
                        textView.setText("");
                        for(Post post: posts)
                        {
                            String data="";
                            data+= "Barcode: "+ post.getBarcode()+"\n";
                            data+= "Product Name: "+post.getProductname()+"\n\n";
                            textView.append(data);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        textView.setText(t.getMessage());
                    }
                });
            }
        });
    }

    public void InsertActivity(View view)
    {
        startActivity(new Intent(getApplicationContext(),InsertActivity.class));
    }
}
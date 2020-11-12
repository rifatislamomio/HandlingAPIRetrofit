package com.example.handlingapiusingretrofit;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertActivity extends AppCompatActivity {

    EditText barcodeET, productNameEt;
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        barcodeET = findViewById(R.id.barcode);
        productNameEt = findViewById(R.id.productName);
        addBtn = findViewById(R.id.btnAdd);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://107.23.74.43/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        addBtn.setOnClickListener(v -> {
            String code = barcodeET.getText().toString();
            String productName = productNameEt.getText().toString();

            if(!code.isEmpty() && !productName.isEmpty())
            {
                Post post = new Post(productName,code);
                Call<Post> call = jsonPlaceHolderApi.addNewEntry(post);
                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(response.code()==200)
                        {
                            Toast.makeText(getApplicationContext(),response.code()+": "+response.body().getMessage(),Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),response.code()+": "+response.body().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
package com.tos.retrofitokhttpcaching.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tos.retrofitokhttpcaching.R;
import com.tos.retrofitokhttpcaching.adapter.PostAdapter;
import com.tos.retrofitokhttpcaching.webapi.APIService;
import com.tos.retrofitokhttpcaching.webapi.RootUrl;
import com.tos.retrofitokhttpcaching.webapi.WebInterface;
import com.tos.retrofitokhttpcaching.webapi.post.PostData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    private WebInterface webInterface;
    Context context;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        webInterface = APIService.createService(WebInterface.class, RootUrl.BASE_URL);
        initView();

    }

    public void initView() {
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        webInterface.getPostData()
                .enqueue(new Callback<List<PostData>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<PostData>> call, @NotNull Response<List<PostData>> response) {
                        if (response.raw().networkResponse() != null) {
                            Log.d(TAG, "onResponse: response is from NETWORK...");
                        } else if (response.raw().cacheResponse() != null
                                && response.raw().networkResponse() == null) {
                            Log.d(TAG, "onResponse: response is from CACHE...");
                        }

                        if (response.code() == 200) {
                            progressBar.setVisibility(View.GONE);
                            List<PostData> postData = response.body();
                            PostAdapter adapter = new PostAdapter(context, postData);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setAdapter(adapter);
                        } else {
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<PostData>> call, @NotNull Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

}

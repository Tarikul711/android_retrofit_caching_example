package com.tos.retrofitokhttpcaching.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tos.retrofitokhttpcaching.R;
import com.tos.retrofitokhttpcaching.adapter.PhotoAdapter;
import com.tos.retrofitokhttpcaching.adapter.PostAdapter;
import com.tos.retrofitokhttpcaching.webapi.APIService;
import com.tos.retrofitokhttpcaching.webapi.RootUrl;
import com.tos.retrofitokhttpcaching.webapi.WebInterface;
import com.tos.retrofitokhttpcaching.webapi.photo.PhotoData;
import com.tos.retrofitokhttpcaching.webapi.post.PostData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    private WebInterface webInterface;
    Context context;
    private static final String TAG = "PhotoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        webInterface = APIService.createService(WebInterface.class, RootUrl.BASE_URL_PHOTO);
        initView();

    }

    public void initView() {
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        webInterface.getPhotoData()
                .enqueue(new Callback<List<PhotoData>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<PhotoData>> call, @NotNull Response<List<PhotoData>> response) {
                        if (response.raw().networkResponse() != null) {
                            Log.d(TAG, "onResponse: response is from NETWORK...");
                        } else if (response.raw().cacheResponse() != null
                                && response.raw().networkResponse() == null) {
                            Log.d(TAG, "onResponse: response is from CACHE...");
                        }

                        if (response.code() == 200) {
                            progressBar.setVisibility(View.GONE);
                            List<PhotoData> postData = response.body();
                            PhotoAdapter adapter = new PhotoAdapter(context, postData);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setAdapter(adapter);
                        } else {
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<PhotoData>> call, @NotNull Throwable t) {

                    }
                });
    }

}

package com.tos.retrofitokhttpcaching.webapi;


import com.tos.retrofitokhttpcaching.webapi.photo.PhotoData;
import com.tos.retrofitokhttpcaching.webapi.post.PostData;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WebInterface {

    @GET(WebMethod.POST_DATA)
    Call<List<PostData>> getPostData();

    @GET(WebMethod.PHOTO_DATA)
    Call<List<PhotoData>> getPhotoData();
}

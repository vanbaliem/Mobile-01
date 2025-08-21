package com.example.vanbaliem_2122110304;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("subjects") // endpoint trên mockapi
    Call<List<SubjectData>> getSubjects();
}

package pl.nazwisko.lab5_start.book;

import android.app.Application;
import android.util.Log;

import java.util.List;

import pl.nazwisko.lab5_start.comment.Comment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class BookWebApplication extends Application {


    BookWebService service;

    public BookWebService getService() {
        return service;
    }

    public void onCreate() {

        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://bookweb.ciolek.info/api/v1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        BookWebService service = retrofit.create(BookWebService.class);

        Call<List<Comment>> repos = service.listComments(20,0);
        repos.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(response.isSuccessful()){
                    List<Comment> comments = response.body();
                    Log.v("Autor","OK"+ comments.size());
                    for (int i=0; i<comments.size();i++){
                        Log.v("Autor", comments.get(i).getAuthor());
                        Log.v("Komentarz", comments.get(i).getComment());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });

    }
}

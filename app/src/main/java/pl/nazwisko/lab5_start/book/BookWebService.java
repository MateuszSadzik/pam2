package pl.nazwisko.lab5_start.book;

import java.util.List;

import pl.nazwisko.lab5_start.comment.Comment;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookWebService {

        @GET("http://bookweb.ciolek.info/api/v1/comments ")
        Call<List<Comment>> listComments(@Query("per-page") int perPage,@Query("page") int page);

}



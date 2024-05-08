package com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.Adapters.MovieDesc_Adapter;
import com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.Model.GetterSetter_Movies;
import com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.R;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MovieDetail_Activity extends AppCompatActivity {

    String str_url = "https://yts.mx/api/v2/movie_details.json?movie_id=";

    String str_getTitle, str_movie_url, str_desc, str_getbgImage, str_getposterImage, str_date_uploaded, str_imdb_code, str_year, str_rating;
    String strUrlT, strHashT, strQualityT, strTypeT, strSizeT, strDateUploadedT;
    int int_movie_id, int_seedsT, int_peersT, int_size_bytesT, int_date_uploaded_unixT;

    boolean loading = true;
    int int_id, int_imdb_code;
    ImageView img_background, img_poster;
    TextView txt_title, txt_rating, txt_description, txt_year;
    RecyclerView recyclerView;
    LinearLayoutManager llm;
    SwipeRefreshLayout pullToRefresh;
    MovieDesc_Adapter adapter;
    ArrayList<GetterSetter_Movies> getterSetter_movies;
    private List<GetterSetter_Movies> listMovie;
    private RequestQueue requestQueue;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudienceNetworkAds.initialize(this);
        setContentView(R.layout.activity_movie_detail_);

        adView = new AdView(this, "694370814627253_694371047960563", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();

       int_id = getIntent().getIntExtra("id", 0);

        img_poster = findViewById(R.id.imageview_poster);
        txt_title = findViewById(R.id.textview_movie_title);
        txt_rating = findViewById(R.id.textview_movie_rating);
        txt_description = findViewById(R.id.textview_description);
        txt_year = findViewById(R.id.textview_movie_uploaded_Date);
        recyclerView = findViewById(R.id.id_recyclerView);
        pullToRefresh = findViewById(R.id.pullToRefresh);

        llm = new GridLayoutManager(MovieDetail_Activity.this, 2);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        pullToRefresh.setRefreshing(true);

        getterSetter_movies = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(MovieDetail_Activity.this);
        parseJson(str_url+int_id, true);

    }

    private void parseJson(final String str_url, final Boolean swipeRefresh) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, str_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        pullToRefresh.setRefreshing(false);

                        try {
                            //   JSONObject jsonObject1 = response.getJSONObject("");
                            JSONObject jsonObject = response.getJSONObject("data");

                            JSONObject jobj = jsonObject.getJSONObject("movie");

                            int_movie_id = jobj.getInt("id");
                            str_imdb_code = jobj.getString("imdb_code");
                            str_getTitle = jobj.getString("title");
                            str_year = jobj.getString("year");
                            str_rating = jobj.getString("rating");
                            str_desc = jobj.getString("description_intro");
                            str_getposterImage = jobj.getString("medium_cover_image");

                            JSONArray jsonArrayT = jobj.getJSONArray("torrents");
                            for (int j = 0; j<jsonArrayT.length(); j++){

                                JSONObject jsonObjectT = jsonArrayT.getJSONObject(j);
                                strUrlT = jsonObjectT.getString("url");
                                strHashT = jsonObjectT.getString("hash");
                                strQualityT = jsonObjectT.getString("quality");
                                strTypeT = jsonObjectT.getString("type");
                                int_seedsT = jsonObjectT.getInt("seeds");
                                int_peersT = jsonObjectT.getInt("peers");
                                strSizeT = jsonObjectT.getString("size");
                                int_size_bytesT = jsonObjectT.getInt("size_bytes");
                                strDateUploadedT = jsonObjectT.getString("date_uploaded");
                                int_date_uploaded_unixT = jsonObjectT.getInt("date_uploaded_unix");

                                getterSetter_movies.add(new GetterSetter_Movies(int_id, strUrlT, strHashT, strQualityT,
                                        strTypeT, int_seedsT, int_peersT, strSizeT, int_size_bytesT,
                                        strDateUploadedT, int_date_uploaded_unixT));

                            }

                            txt_title.setText(str_getTitle);
                            txt_year.setText(str_year);
                            txt_description.setText(str_desc);
                            txt_rating.setText(str_rating);

                            if ( str_getposterImage == null) {
                                Glide.with(MovieDetail_Activity.this).load(R.mipmap.ic_launcher_round).into(img_poster);
                                Log.d("Adapter Data Is Null", "");
                            } else {

                                Glide.with(MovieDetail_Activity.this)
                                        .load(str_getposterImage)
                                        //.load("http://via.placeholder.com/300.png")
                                        .into(img_poster);
                                //Log.d("Showing Images", getterSetter_movies.getMedium_cover_image());
                            }

                            adapter = new MovieDesc_Adapter(MovieDetail_Activity.this,getterSetter_movies);
                            recyclerView.setAdapter( adapter );

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
        requestQueue.cancelAll(TAG);

    }
}
package com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.Adapters.MovieList_Adapter;
import com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.Model.GetterSetter_Movies;
import com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.R;
import com.facebook.ads.AudienceNetworkAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class PopularMoviesFragment extends Fragment {

    int movielimit = 20;
    String str_url = "https://yts.mx/api/v2/list_movies.json?sort_by=" + movielimit;
    int totalPages = 1;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean loading = true;
    int page = 1;
    int int_id, int_year, int_rating;
    String str_urll, str_imdb_code, str_title, str_title_english, str_medium_cover_image, str_synopsis, str_data_andTime, str_background_image;
    String str_urlT, str_hashT, str_qualityT, str_typeT, str_sizeT, str_date_uploadedT;
    int int_seedsT, int_peersT, int_size_bytesT, int_date_uploaded_unixT;
    LinearLayoutManager llm;
    SwipeRefreshLayout pullToRefresh;
    private RecyclerView recyclerView;
    MovieList_Adapter adapter;
    GetterSetter_Movies movies;
    ArrayList<GetterSetter_Movies> getterSetter_movies;
    private List<GetterSetter_Movies> listMovie;
    private RequestQueue requestQueue;
    private AdView adView;

    public PopularMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AudienceNetworkAds.initialize(getContext());
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_popular_movies, container, false);

        adView = new AdView(getContext(), "694370814627253_694371047960563", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) root.findViewById(R.id.banner_container);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();

        pullToRefresh = root.findViewById(R.id.pullToRefresh);
        recyclerView = root.findViewById(R.id.id_rec_popular_movie);

        llm = new GridLayoutManager(getContext(), 3);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        pullToRefresh.setRefreshing(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = llm.getChildCount();
                totalItemCount = llm.getItemCount();
                pastVisiblesItems = llm.findFirstVisibleItemPosition();
                if (loading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = false;
                        Log.e("TAGl", "inside loading tot & page = " + totalPages + " " + page);
                        if (page < totalPages) {
                            Log.e("TAGl", "inside page<=totalPage");
                            Log.e("TAGl", "last inside tot & page = " + totalPages + " " + page);
                            page++;
                            //  paramsModel.add(new ParamsModel("page", page + ""));
                            parseJson(str_url + "&page=" + page, false);

                        }
                    }
                }
            }
        });

        pullToRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i("TAG", "onRefresh called from SwipeRefreshLayout");
                        parseJson(str_url, true);
                        pullToRefresh.setRefreshing(true);
                    }
                }
        );

        //   mProgressBar.setVisibility(View.VISIBLE);
        getterSetter_movies = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());
        parseJson(str_url, true);

        return root;
    }

    private void parseJson(final String str_url, final Boolean swipeRefresh) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, str_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        pullToRefresh.setRefreshing(false);

                        // mProgressBar.setVisibility(View.GONE);
                        loading = true;

                        // List<GetterSetter_Movies> movieList = listModel.getData().getMovies();
                        try {
                            //   JSONObject jsonObject1 = response.getJSONObject("");
                            JSONObject jsonObject = response.getJSONObject("data");
                            int int_movieCount = jsonObject.getInt("movie_count");
                            int int_limit = jsonObject.getInt("limit");
                            int int_page_number= jsonObject.getInt("page_number");

                            JSONArray jsonArray = jsonObject.getJSONArray("movies");

                            for (int i = 0; i<jsonArray.length(); i++){

                                JSONObject jobj = jsonArray.getJSONObject(i);
                                int_id = jobj.getInt("id");
                                str_urll = jobj.getString("url");
                                str_imdb_code = jobj.getString("imdb_code");
                                str_title = jobj.getString("title");
                                str_title_english = jobj.getString("title_english");
                                str_medium_cover_image = jobj.getString("medium_cover_image");
                                str_synopsis = jobj.getString("synopsis");
                                str_data_andTime = jobj.getString("date_uploaded");
                                str_background_image = jobj.getString("background_image");
                                int_rating = jobj.getInt("rating");
                                int_year = jobj.getInt("year");
                                //   int int_movie_count = jobj.getInt("movie_count");
                                JSONArray jsonArrayTorrent = jobj.getJSONArray("torrents");
                                for (int j = 0; j<jsonArrayTorrent.length(); j++){

                                    JSONObject jsonObjectTorrent = jsonArrayTorrent.getJSONObject(j);
                                    str_urlT = jsonObjectTorrent.getString("url");
                                    str_hashT = jsonObjectTorrent.getString("hash");
                                    str_qualityT = jsonObjectTorrent.getString("quality");
                                    str_typeT = jsonObjectTorrent.getString("type");
                                    int_seedsT = jsonObjectTorrent.getInt("seeds");
                                    int_peersT = jsonObjectTorrent.getInt("peers");
                                    str_sizeT = jsonObjectTorrent.getString("size");
                                    int_size_bytesT = jsonObjectTorrent.getInt("size_bytes");
                                    str_date_uploadedT = jsonObjectTorrent.getString("date_uploaded");
                                    int_date_uploaded_unixT = jsonObjectTorrent.getInt("date_uploaded_unix");


                                }

                                getterSetter_movies.add(new GetterSetter_Movies(int_id, str_urll, str_imdb_code, str_title,
                                        str_title_english, str_medium_cover_image, int_year, int_movieCount, int_limit,
                                        str_synopsis, str_data_andTime, int_rating, str_background_image,
                                        str_urlT, str_hashT, str_qualityT, str_typeT, int_seedsT, int_peersT, str_sizeT, int_size_bytesT, str_date_uploadedT,
                                        int_date_uploaded_unixT));

                            }

                            // Log.e("LatestMovies", "movieCount = " + int_movieCount + " movieLimit = " + int_limit + " totalPage = " + totalPages);
                            if (int_movieCount % int_limit != 0) {
                                totalPages++;
                            }

                            if (getterSetter_movies != null) {
                                //   layout_internet.setVisibility(View.GONE);
                                if (swipeRefresh) {
                                    pullToRefresh.setRefreshing(false);
                                    adapter = new MovieList_Adapter(getContext(),getterSetter_movies);
                                } else {
                                    //adapter.addItems(getterSetter_movies);
                                }
                            } else {
                                pullToRefresh.setRefreshing(true);
                            }


                            adapter = new MovieList_Adapter(getContext(),getterSetter_movies);
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
        // MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

        requestQueue.add(jsonObjectRequest);
        requestQueue.cancelAll(TAG);
    }
}
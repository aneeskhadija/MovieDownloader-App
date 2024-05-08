package com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.Activities.MovieDetail_Activity;
import com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.Model.GetterSetter_Movies;
import com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.R;

import java.util.List;

public class MovieList_Adapter extends RecyclerView.Adapter<MovieList_Adapter.MovieListViewHolder> {

    private Context mContext;
    private List<GetterSetter_Movies> listMovie;
    String str_title, strImage, str_synopsis, str_date_andTime, str_background_image, str_url, str_year;
    String str_urlT, str_hashT, str_qualityT, str_typeT, str_sizeT, str_date_uploadedT;
    public int int_seedsT, int_peersT, int_size_bytesT, int_date_uploaded_unixT;
    public int int_id, movieCount, movieLimit, int_rating;
    GetterSetter_Movies getterSetter_movies;

    public MovieList_Adapter(Context mContext, List<GetterSetter_Movies> listMovie) {
        this.mContext = mContext;
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.movie_list_view, viewGroup, false);

        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieListViewHolder hotelListViewHolder, int position) {

        getterSetter_movies = listMovie.get(position);

        hotelListViewHolder.txt_MovieName.setText(getterSetter_movies.getTitle());
        movieCount = getterSetter_movies.getMovie_count();
        movieLimit = getterSetter_movies.getLimit();
        int_rating = getterSetter_movies.getRating();
        str_title = getterSetter_movies.getTitle();
        str_url = getterSetter_movies.getUrl();
        str_synopsis = getterSetter_movies.getSynopsis();
        str_background_image = getterSetter_movies.getBackground_image();
        str_date_andTime = getterSetter_movies.getDate_uploaded();
        str_year = String.valueOf(getterSetter_movies.year);
        hotelListViewHolder.txt_Years.setText(str_year);
        strImage = getterSetter_movies.getMedium_cover_image();
        String string_image = strImage;

        if ( strImage == null) {
            Glide.with(mContext).load(R.mipmap.ic_launcher_round).into(hotelListViewHolder.imageView);
            Log.d("Adapter Data Is Null", "");
        } else {

            Glide.with(mContext)
                    .load(string_image)
                    //.load("http://via.placeholder.com/300.png")
                    .into(hotelListViewHolder.imageView);
                    Log.d("Showing Images", getterSetter_movies.getMedium_cover_image());
        }
       // hotelListViewHolder.imageView.setOnClickListener(clickListener);

        hotelListViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = hotelListViewHolder.getAdapterPosition();
                GetterSetter_Movies feedItem = listMovie.get(position);
                int intID = feedItem.getId();

                Intent intnt_new = new Intent(mContext, MovieDetail_Activity.class);
                intnt_new.putExtra("id", intID);


                mContext.startActivity(intnt_new);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class MovieListViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView txt_MovieName, txt_CityName, txt_Years;

        public MovieListViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.id_imageView);
            txt_MovieName = itemView.findViewById(R.id.id_Movie_name);
           // txt_CityName = itemView.findViewById(R.id.id_city_name);
            txt_Years = itemView.findViewById(R.id.id_year);
        }
    }

}

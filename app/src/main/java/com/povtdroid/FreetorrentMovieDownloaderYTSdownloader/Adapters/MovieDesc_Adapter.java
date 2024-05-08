package com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.Adapters;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.Model.GetterSetter_Movies;
import com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.R;

import java.util.List;

public class MovieDesc_Adapter extends RecyclerView.Adapter<MovieDesc_Adapter.MovieDescViewHolder> {

    private Context mContext;
    private List<GetterSetter_Movies> listMovie;
    GetterSetter_Movies getterSetter_movies;

    String str_url, str_hash, str_quality, str_type, str_size, str_date_uploaded;
    int int_date_uploaded_unix, int_peers, int_seeds;

    public MovieDesc_Adapter(Context mContext, List<GetterSetter_Movies> listMovie) {
        this.mContext = mContext;
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public MovieDescViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.download_desc, viewGroup, false);

        return new MovieDescViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieDescViewHolder movieDescViewHolder, int position) {

        getterSetter_movies = listMovie.get(position);

        str_url = getterSetter_movies.getUrl();
        str_hash = getterSetter_movies.getHash();
        str_quality = getterSetter_movies.getQuality();
        str_type = getterSetter_movies.getType();
        str_size = getterSetter_movies.getSize();
        str_date_uploaded = getterSetter_movies.getDate_uploaded();

        int_date_uploaded_unix = getterSetter_movies.getDate_uploaded_unix();
        int_peers = getterSetter_movies.getPeers();
        int_seeds = getterSetter_movies.getSeeds();

        movieDescViewHolder.txt_size.setText("Size: "+getterSetter_movies.getSize());
        movieDescViewHolder.txt_peers.setText("Peers: "+getterSetter_movies.getPeers());
        movieDescViewHolder.txt_seeds.setText("Seeds: "+getterSetter_movies.getSeeds());
        movieDescViewHolder.btn_download.setText(getterSetter_movies.getQuality()+" Downloads");


        movieDescViewHolder.btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = movieDescViewHolder.getAdapterPosition();
                GetterSetter_Movies feedItem = listMovie.get(position);
                int intID = feedItem.getId();
                String url = feedItem.getUrl();

                if (url != null) {
                    Log.e("TAG", "url starts with magnet");
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    PackageManager manager = mContext.getPackageManager();
                    List<ResolveInfo> infos = manager.queryIntentActivities(browserIntent, 0);
                    if (infos.size() > 0) {
                        mContext.startActivity(browserIntent);
                        Log.e("TAG", "yes act to handle");

                    } else {
                        Log.e("TAG", "No act to handle");
                        new AlertDialog.Builder(mContext, R.layout.my_dialog_theme)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Uri uri = Uri.parse("market://details?id=" + "com.bittorrent.client");
                                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                        try {
                                            mContext.startActivity(goToMarket);
                                        } catch (ActivityNotFoundException e) {
                                            Toast.makeText(mContext, "install a torrent app from playstore to download this movies", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .show();
                    }
                } else {
                    Log.e("TAG", "url does not starts with magnet");
                }
            }
        });
    }

    public void openMagneturi(String url, final Context c) {
        Log.e("TAG", "openMagneturi magnet");


    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class MovieDescViewHolder extends RecyclerView.ViewHolder{

       // ImageView imageView;
        TextView txt_size, txt_seeds, txt_peers;
        Button btn_download;

        public MovieDescViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_size = itemView.findViewById(R.id.id_size);
            txt_seeds = itemView.findViewById(R.id.id_seeds);
            // txt_CityName = itemView.findViewById(R.id.id_city_name);
            txt_peers = itemView.findViewById(R.id.id_peers);
            btn_download = itemView.findViewById(R.id.id_btn_download);
        }
    }
}

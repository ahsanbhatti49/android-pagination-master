package mvp.bhatti.com.mvplogin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mvp.bhatti.com.mvplogin.R;
import mvp.bhatti.com.mvplogin.model.MovieResponse;
import mvp.bhatti.com.mvplogin.model.Result;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder> {

    private List<Result> movieList;
    private Context context;


    public MoviesAdapter(List<Result> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_movies, parent, false);
        return new MoviesHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesHolder holder, int position) {

        holder.tvTitle.setText(movieList.get(position).getTitle());
        holder.tvReleaseDate.setText(movieList.get(position).getReleaseDate());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + movieList
                .get(position).getPosterPath()).into(holder.ivMovie);
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public class MoviesHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvReleaseDate;
        ImageView ivMovie;

        public MoviesHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            tvReleaseDate = (TextView) v.findViewById(R.id.tvReleaseDate);
            ivMovie = (ImageView) v.findViewById(R.id.ivMovie);
        }
    }


}

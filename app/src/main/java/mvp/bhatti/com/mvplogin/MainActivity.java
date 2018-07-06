package mvp.bhatti.com.mvplogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.bhatti.com.mvplogin.adapter.MoviesAdapter;
import mvp.bhatti.com.mvplogin.model.MovieResponse;
import mvp.bhatti.com.mvplogin.model.Result;
import mvp.bhatti.com.mvplogin.presenter.MainPresenter;
import mvp.bhatti.com.mvplogin.view.MainViewInterface;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;
    @BindView(R.id.pb_progress)
    ProgressBar progressBar;
    private String TAG = "MainActivity";
    RecyclerView.Adapter adapter;
    MainPresenter mainPresenter;
    private Boolean isScrolling = false;
    private RecyclerView.LayoutManager manager;
    int currentItems, totalItems, scrolledItems;
    private ArrayList<Result> movieResponses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        movieResponses = new ArrayList<>();
        setupMVP();
        setupViews();
        getMovieList();
    }

    private void setupMVP() {
        mainPresenter = new MainPresenter(this);
    }

    private void setupViews() {
        manager = new GridLayoutManager(this, 2);
        rvMovies.setLayoutManager(manager);
        rvMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrolledItems = ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (isScrolling && (currentItems + scrolledItems == totalItems)) {
                    isScrolling=false;
                    getMovieList();
                }
            }
        });
    }
    private void getMovieList() {
        mainPresenter.getMovies();

    }

    @Override
    public void showToast(String str) {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayMovies(MovieResponse movieResponse) {
        if (movieResponse != null) {
            movieResponses.addAll(movieResponse.getResults());
            Log.d(TAG, movieResponse.getResults().get(1).getTitle());
            if (adapter == null) {
                adapter = new MoviesAdapter(movieResponses, MainActivity.this);
                rvMovies.setAdapter(adapter);
            }
            else{
                adapter.notifyDataSetChanged();
            }
        } else {
            Log.d(TAG, "Movies response null");
        }
    }

    @Override
    public void displayError(String e) {
        showToast(e);
    }
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }
    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}

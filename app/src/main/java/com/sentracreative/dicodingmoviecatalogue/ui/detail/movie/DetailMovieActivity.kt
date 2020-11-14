package com.sentracreative.dicodingmoviecatalogue.ui.detail.movie

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sentracreative.dicodingmoviecatalogue.R
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.viewmodel.ViewModelFactory
import com.sentracreative.dicodingmoviecatalogue.vo.Status
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_movie.progress_bar
import kotlinx.android.synthetic.main.fragment_movie.*

class DetailMovieActivity : AppCompatActivity() {
    lateinit var viewModel : DetailMovieViewModel
    private var menu: Menu? = null

    companion object{
        const val EXTRA_ID = ""
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory)[DetailMovieViewModel::class.java]

        val bundle = intent.extras

        if (bundle != null){
            val movieId = bundle.getString(EXTRA_ID)
            if (movieId != null){

                viewModel.setSelectedMovie(movieId)

                viewModel.selectedMovie.observe(this, Observer { movie ->
                    if (movie != null){
                        when(movie.status){
                            Status.LOADING -> progress_bar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                progress_bar.visibility = View.GONE
                                tv_title.text = movie.data?.title
                                tv_year.text = movie.data?.year
                                tv_genre.text = movie.data?.genre
                                tv_duration.text = movie.data?.duration
                                tv_rated.text = movie.data?.rated
                                tv_desc.text = movie.data?.description
                                tv_rating.text = movie.data?.score.toString()

                                Glide.with(this)
                                    .load(movie.data?.urlImage)
                                    .apply(
                                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                                            .error(R.drawable.ic_error)
                                    )
                                    .into(img_poster)

                                btn_share.setOnClickListener {
                                    val shareIntent = Intent()
                                    shareIntent.action = Intent.ACTION_SEND
                                    shareIntent.putExtra(Intent.EXTRA_TEXT,movie.data?.title)
                                    shareIntent.type = "text/plain"
                                    startActivity(Intent.createChooser(shareIntent,"Send To"))
                                }
                            }
                            Status.ERROR ->  {
                                progress_bar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }


                })

            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail,menu)
        this.menu = menu

        viewModel.selectedMovie.observe(this, Observer { movie ->
            if (movie != null){
                when(movie.status){
                    Status.LOADING -> progress_bar.visibility = View.VISIBLE

                    Status.SUCCESS -> if (movie.data != null){
                        progress_bar.visibility = View.GONE
                        val state = movie.data.bookmarked
                        setBookmarkState(state)
                    }

                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            viewModel.setBookmark()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkState(state : Boolean){
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_on)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_off)
        }
    }

}
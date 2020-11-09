package com.sentracreative.dicodingmoviecatalogue.ui.detail.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sentracreative.dicodingmoviecatalogue.R
import com.sentracreative.dicodingmoviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_ID = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this,factory)[DetailMovieViewModel::class.java]

        val bundle = intent.extras

        if (bundle != null){
            val movieId = bundle.getString(EXTRA_ID)
            if (movieId != null){
                viewModel.setSelectedMovie(movieId)

                val movie = viewModel.getSelectedMovie()

                tv_title.text = movie.title
                tv_year.text = movie.year
                tv_genre.text = movie.genre
                tv_duration.text = movie.duration
                tv_rated.text = movie.rated
                tv_desc.text = movie.description
                tv_rating.text = movie.score.toString()

                Glide.with(this)
                    .load(movie.url_image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_poster)

                btn_share.setOnClickListener {
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.putExtra(Intent.EXTRA_TEXT,movie.title)
                    shareIntent.type = "text/plain"
                    startActivity(Intent.createChooser(shareIntent,"Send To"))
                }

            }
        }

    }
}
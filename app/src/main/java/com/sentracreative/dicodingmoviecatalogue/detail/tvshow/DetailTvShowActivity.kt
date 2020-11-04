package com.sentracreative.dicodingmoviecatalogue.detail.tvshow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sentracreative.dicodingmoviecatalogue.R
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_ID = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[DetailTvShowViewModel::class.java]
        val bundle = intent.extras

        if (bundle != null){
            val tvShowId = bundle.getString(EXTRA_ID)

            if (tvShowId != null){
                viewModel.setSelectedTvShow(tvShowId)

                val tvShow = viewModel.getSelectedTvShow()

                tv_title.text = tvShow.title
                tv_year.text = tvShow.year
                tv_genre.text = tvShow.genre
                tv_episode.text = tvShow.episode
                tv_desc.text = tvShow.description
                tv_rating.text = tvShow.rating.toString()

                Glide.with(this)
                    .load(tvShow.url_image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_poster)

                btn_share.setOnClickListener {
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.putExtra(Intent.EXTRA_TEXT,tvShow.title)
                    shareIntent.type = "text/plain"
                    startActivity(Intent.createChooser(shareIntent,"Send To"))
                }

            }

        }

    }
}
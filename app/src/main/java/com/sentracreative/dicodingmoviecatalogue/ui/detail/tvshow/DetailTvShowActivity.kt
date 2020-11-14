package com.sentracreative.dicodingmoviecatalogue.ui.detail.tvshow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sentracreative.dicodingmoviecatalogue.R
import com.sentracreative.dicodingmoviecatalogue.viewmodel.ViewModelFactory
import com.sentracreative.dicodingmoviecatalogue.vo.Status
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import kotlinx.android.synthetic.main.activity_detail_tv_show.btn_share
import kotlinx.android.synthetic.main.activity_detail_tv_show.img_poster
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_desc
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_genre
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_rating
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_title
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_year

class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var viewModel : DetailTvShowViewModel
    private var menu: Menu? = null

    companion object{
        const val EXTRA_ID = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory)[DetailTvShowViewModel::class.java]
        val bundle = intent.extras

        if (bundle != null){
            val tvShowId = bundle.getString(EXTRA_ID)

            if (tvShowId != null){

                viewModel.setSelectedTvShow(tvShowId)

                viewModel.selectedTvShow.observe(this, Observer {tvShow ->
                    if (tvShow != null){

                        when(tvShow.status){
                            Status.LOADING -> progressBar.visibility = View.VISIBLE

                            Status.SUCCESS -> {
                                progressBar.visibility = View.GONE
                                tv_title.text = tvShow.data?.title
                                tv_year.text = tvShow.data?.year
                                tv_genre.text = tvShow.data?.genre
                                tv_episode.text = tvShow.data?.episode
                                tv_desc.text = tvShow.data?.description
                                tv_rating.text = tvShow.data?.rating.toString()

                                Glide.with(this)
                                    .load(tvShow.data?.urlImage)
                                    .apply(
                                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                                            .error(R.drawable.ic_error)
                                    )
                                    .into(img_poster)

                                btn_share.setOnClickListener {
                                    val shareIntent = Intent()
                                    shareIntent.action = Intent.ACTION_SEND
                                    shareIntent.putExtra(Intent.EXTRA_TEXT,tvShow.data?.title)
                                    shareIntent.type = "text/plain"
                                    startActivity(Intent.createChooser(shareIntent,"Send To"))
                                }
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

        viewModel.selectedTvShow.observe(this, Observer { tvshow ->
            if (tvshow != null){
                when(tvshow.status){
                    Status.LOADING -> progressBar.visibility = View.VISIBLE

                    Status.SUCCESS -> if (tvshow.data != null){
                        progressBar.visibility = View.GONE
                        val state = tvshow.data.bookmarked
                        setBookmarkState(state)
                    }

                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
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
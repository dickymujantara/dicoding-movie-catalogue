package com.sentracreative.dicodingmoviecatalogue.ui.bookmark.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sentracreative.dicodingmoviecatalogue.R
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.ui.detail.movie.DetailMovieActivity
import kotlinx.android.synthetic.main.items_movie.view.*

class BookmarkMovieAdapter internal constructor(): PagedListAdapter<MovieEntity,BookmarkMovieAdapter.BookmarkMovieHolder>(DIFF_CALLBACK) {
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkMovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_movie,parent,false)
        return BookmarkMovieHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkMovieHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null){
            holder.bind(movie)
        }
    }

    fun getSwipeData(swipedPosition : Int) : MovieEntity = getItem(swipedPosition)!!

    class BookmarkMovieHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(movie : MovieEntity){
            with(itemView){
                tv_title.text = movie.title
                tv_year.text = movie.year
                tv_rated.text = movie.rated
                tv_duration.text = movie.duration
                tv_genre.text = movie.duration
                tv_score.text = movie.score.toString()

                setOnClickListener {
                    val intent = Intent(context, DetailMovieActivity::class.java).apply {
                        putExtra(DetailMovieActivity.EXTRA_ID, movie.movieId)
                    }
                    context.startActivity(intent)
                }

                Glide.with(context)
                    .load(movie.urlImage)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_poster)
            }
        }
    }

}
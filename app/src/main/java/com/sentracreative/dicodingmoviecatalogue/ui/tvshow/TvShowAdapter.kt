package com.sentracreative.dicodingmoviecatalogue.ui.tvshow

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
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.ui.detail.tvshow.DetailTvShowActivity
import kotlinx.android.synthetic.main.items_tv_show.view.*
import kotlinx.android.synthetic.main.items_tv_show.view.img_poster
import kotlinx.android.synthetic.main.items_tv_show.view.tv_genre
import kotlinx.android.synthetic.main.items_tv_show.view.tv_score
import kotlinx.android.synthetic.main.items_tv_show.view.tv_title
import kotlinx.android.synthetic.main.items_tv_show.view.tv_year

class TvShowAdapter internal constructor(): PagedListAdapter<TvShowEntity,TvShowAdapter.TvShowViewHolder>(
    DIFF_CALLBACK){

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>(){
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_tv_show,parent,false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null){
            holder.bind(tvShow)
        }
    }

    class TvShowViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(tvShow : TvShowEntity){
            with(itemView){
                tv_title.text = tvShow.title
                tv_year.text = tvShow.year
                tv_episode.text = tvShow.episode
                tv_genre.text = tvShow.genre
                tv_score.text = tvShow.rating.toString()

                setOnClickListener {
                    val intent = Intent(context, DetailTvShowActivity::class.java).apply {
                        putExtra(DetailTvShowActivity.EXTRA_ID, tvShow.tvShowId)
                    }
                    context.startActivity(intent)
                }

                Glide.with(context)
                    .load(tvShow.urlImage)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_poster)
            }
        }
    }

}
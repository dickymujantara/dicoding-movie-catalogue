package com.sentracreative.dicodingmoviecatalogue.ui.bookmark.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sentracreative.dicodingmoviecatalogue.R
import com.sentracreative.dicodingmoviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_bookmark_movie.*
import kotlinx.android.synthetic.main.fragment_tv_show.*
import kotlinx.android.synthetic.main.fragment_tv_show.progress_bar

class BookmarkMovieFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmark_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factory)[BookmarkMovieViewModel::class.java]
            val adapter = BookmarkMovieAdapter()

            progress_bar.visibility = View.VISIBLE

            viewModel.getMovies().observe(this, Observer { movie ->
                progress_bar.visibility = View.GONE
                adapter.setMovies(movie)
                adapter.notifyDataSetChanged()
            })

            with(rv_movie_bookmark){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                rv_movie_bookmark.adapter = adapter
            }

        }

    }

}
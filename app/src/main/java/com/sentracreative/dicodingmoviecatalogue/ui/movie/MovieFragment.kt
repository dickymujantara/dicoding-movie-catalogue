package com.sentracreative.dicodingmoviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sentracreative.dicodingmoviecatalogue.R
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null){
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val movies = viewModel.getMovies()
            val adapter = MovieAdapter()
            adapter.setMovies(movies)

            with(rv_movie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                rv_movie.adapter = adapter
            }

        }

    }
}
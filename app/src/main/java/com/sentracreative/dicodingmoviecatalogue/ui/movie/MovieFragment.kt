package com.sentracreative.dicodingmoviecatalogue.ui.movie

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
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.progress_bar
import kotlinx.android.synthetic.main.fragment_tv_show.*

class MovieFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val adapter = MovieAdapter()

            progress_bar.visibility = View.VISIBLE

            viewModel.getMovies().observe(this, Observer {
                progress_bar.visibility = View.GONE
                adapter.setMovies(it)
                adapter.notifyDataSetChanged()
            })

            with(rv_movie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                rv_movie.adapter = adapter
            }

        }

    }
}
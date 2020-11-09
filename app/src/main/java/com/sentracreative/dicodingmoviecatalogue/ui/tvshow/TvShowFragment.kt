package com.sentracreative.dicodingmoviecatalogue.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sentracreative.dicodingmoviecatalogue.R
import com.sentracreative.dicodingmoviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            val tvShows = viewModel.getTvShows()
            val adapter = TvShowAdapter()
            adapter.setTvShow(tvShows)

            with(rv_tv_show){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                rv_tv_show.adapter = adapter
            }

        }

    }

}
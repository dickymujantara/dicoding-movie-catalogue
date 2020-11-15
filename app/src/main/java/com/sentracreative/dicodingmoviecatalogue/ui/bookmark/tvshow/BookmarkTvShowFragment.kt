package com.sentracreative.dicodingmoviecatalogue.ui.bookmark.tvshow

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
import kotlinx.android.synthetic.main.fragment_bookmark_tv_show.*
import kotlinx.android.synthetic.main.fragment_tv_show.*
import kotlinx.android.synthetic.main.fragment_tv_show.progress_bar

class BookmarkTvShowFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmark_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null){

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factory)[BookmarkTvShowViewModel::class.java]
            val adapter = BookmarkTvShowAdapter()
            progress_bar.visibility = View.VISIBLE

            viewModel.getTvShows().observe(this, Observer { tvshow ->
                if (tvshow != null){
                    progress_bar.visibility = View.GONE
                    adapter.submitList(tvshow)
                    adapter.notifyDataSetChanged()
                }
            })

            with(rv_tv_show_bookmark){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                rv_tv_show_bookmark.adapter = adapter
            }

        }

    }

}
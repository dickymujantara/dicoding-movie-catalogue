package com.sentracreative.dicodingmoviecatalogue.ui.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sentracreative.dicodingmoviecatalogue.R
import kotlinx.android.synthetic.main.activity_bookmark.*

class BookmarkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        supportActionBar?.title = "Favourites"

        val sectionsPagerAdapter = SectionsPageAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

    }
}
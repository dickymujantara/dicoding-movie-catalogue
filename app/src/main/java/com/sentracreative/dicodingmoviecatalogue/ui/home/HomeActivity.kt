package com.sentracreative.dicodingmoviecatalogue.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sentracreative.dicodingmoviecatalogue.R
import com.sentracreative.dicodingmoviecatalogue.ui.bookmark.BookmarkActivity
import com.sentracreative.dicodingmoviecatalogue.ui.movie.MovieFragment
import com.sentracreative.dicodingmoviecatalogue.ui.tvshow.TvShowFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private var currentId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportFragmentManager.beginTransaction().replace(R.id.home_frame, MovieFragment()).commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.menu_movie -> fragmentCheck(R.id.menu_movie, MovieFragment())
            R.id.menu_tvshow -> fragmentCheck(R.id.menu_tvshow, TvShowFragment())
            R.id.menu_bookmark -> {
                val intent = Intent(this, BookmarkActivity::class.java)
                startActivity(intent)
            }
        }
        currentId = it.itemId
        true
    }

    private fun fragmentCheck(fragmentId: Int, fragment: Fragment) {
        if (currentId != fragmentId) {
            supportFragmentManager.beginTransaction().replace(R.id.home_frame, fragment).commit()
        }
    }

}
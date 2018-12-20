package com.example.qiboxia.testapp

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*

class ScrollingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
//        setSupportActionBar(toolbar)

        vp.adapter = Adapter(this)
        tab_layout.setupWithViewPager(vp)
    }

    class Adapter(val context : Context): PagerAdapter(){

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = RecyclerView(context)
            view.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                    val text = TextView(context)
                    text.text  = "13213"
                    text.setPadding(10 , 10 , 10 ,10 )
                    return object : RecyclerView.ViewHolder(text) {}
                }

                override fun getItemCount(): Int {
                    return 100
                }

                override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                }

            }
            view.layoutManager = LinearLayoutManager(context)
            container.addView(view)
            return view
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return 2
        }

    }
}

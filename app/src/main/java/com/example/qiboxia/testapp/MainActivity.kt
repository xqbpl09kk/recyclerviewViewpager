package com.example.qiboxia.testapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.adapter = MAdapter(applicationContext)
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
    }

    class MAdapter(val context : Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            when(p1){
                0 -> { return object : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_header , p0 , false)) {
                    init{
                        (itemView as RecyclerView).adapter =object: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
                            override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
                                return object : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item1 , p0 , false)) {}
                            }

                            override fun getItemCount(): Int { return 4 }

                            override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {}

                        }
                        itemView.layoutManager = object : GridLayoutManager(context , 2){
                            override fun canScrollHorizontally(): Boolean {
                                return false
                            }

                            override fun canScrollVertically(): Boolean {
                                return false
                            }
                        }
                    }
                } }
                1 ->{
                    return object:RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tab , p0 , false)){}
                }
                else ->{
                    return object:RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pager , p0 , false)){
                        init {
                            val pager = itemView as ViewPager
                            val adapter = MPagerAdapter(context)
                            pager.adapter = adapter
                            pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
                                override fun onPageScrollStateChanged(p0: Int) {

                                }

                                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                                }

                                override fun onPageSelected(p0: Int) {
//                                    val layoutParams = pager.layoutParams
//                                    layoutParams.height = adapter.getHeight(p0)
//                                    pager.layoutParams = layoutParams
//                                    notifyDataSetChanged()
                                }

                            })
                        }
                    }
                }
            }
        }

        override fun getItemCount(): Int {
            return 3
        }

        override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        }

        override fun getItemViewType(position: Int): Int {
            return position
        }

    }
    class MPagerAdapter(val context: Context): PagerAdapter(){

        private val map : HashMap<Int , WeakReference<View>> = HashMap()
        fun getHeight(position : Int) :Int{
            return map[position]?.get()?.height ?:0
        }

        override fun isViewFromObject(p0: View, p1: Any): Boolean {
            return p1 == p0
        }

        override fun getCount(): Int {
            return 2
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val recyclerView = LayoutInflater.from(context).inflate(R.layout.item_header , container ,false) as RecyclerView
            recyclerView.adapter = ContentAdapter(context , (position +1)* 50)
            recyclerView.layoutManager =object : LinearLayoutManager(context){
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            container.addView(recyclerView)

//            val layoutParams = container.layoutParams
//            layoutParams.height = recyclerView.computeVerticalScrollRange()
//            container.layoutParams = layoutParams
            map[position] = WeakReference<View>(recyclerView)
            return recyclerView
        }

    }

    class ContentAdapter(val context: Context , var count:Int )  :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            return object:RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_content ,p0 , false)) {}
        }

        override fun getItemCount(): Int {
            return count
        }

        override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
            (p0.itemView as TextView).text = "第$p1 行"
        }

    }
}

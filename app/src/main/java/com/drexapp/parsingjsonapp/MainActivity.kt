package com.drexapp.parsingjsonapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.drexapp.parsingjsonapp.DetailActivity.Companion.POST_EXTRA
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private val adapterPost = AdapterPost()
    private val listPost = ArrayList<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapterPost

        parsingJSON()
        sendData()
    }

    private fun parsingJSON() {
        val url = "https://pixabay.com/api/?key=17255522-1ff8c1d858bf1a18fbbd937a4&q=bird&image_type=photo&pretty=true"
        val request = JsonObjectRequest(Request.Method.GET, url, null,
        Response.Listener {
            try {
                val jsonArray = it.getJSONArray("hits")
                for (i in 0 until jsonArray.length()) {
                    val jsonObjectHit = jsonArray.getJSONObject(i)
                    val imageUser = jsonObjectHit.getString("userImageURL")
                    val username = jsonObjectHit.getString("user")
                    val imagePost = jsonObjectHit.getString("webformatURL")
                    val like = jsonObjectHit.getInt("likes")
                    listPost.add(Post(imageUser, username, imagePost, like))
                }
                adapterPost.setData(listPost)
            }catch (e: JSONException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        },
        Response.ErrorListener {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            it.printStackTrace()
        })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    }

    private fun sendData() {
        adapterPost.setOnItemClickListener(object : AdapterPost.OnItemClickListener {
            override fun onItemClicked(post: Post) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                val parcelPost = Post(
                    post.imageUser,
                    post.username,
                    post.imagePost,
                    post.like
                )
                intent.putExtra(POST_EXTRA, parcelPost)
                startActivity(intent)
            }
        })
    }
}

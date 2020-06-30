package com.drexapp.parsingjsonapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val POST_EXTRA = "post_extra"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val getIntent = intent.getParcelableExtra<Post>(POST_EXTRA)

        Glide.with(this).load(getIntent?.imageUser).into(detail_image_user)
        detail_username.text = getIntent?.username
        Glide.with(this).load(getIntent?.imagePost).into(detail_image_post)
        detail_like.text = "${getIntent?.like} Like"
    }
}

package com.drexapp.parsingjsonapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list.view.*

class AdapterPost : RecyclerView.Adapter<AdapterPost.ListViewHolder>() {

    private val listPost = ArrayList<Post>()
    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setData(itemData: ArrayList<Post>) {
        listPost.clear()
        listPost.addAll(itemData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(post: Post) {
            with(itemView) {
                Glide.with(context).load(post.imageUser).into(item_list_image_user)
                item_list_username.text = post.username
                Glide.with(context).load(post.imagePost).into(item_list_image_post)
                item_list_like.text = "${post.like} Like"
                itemView.setOnClickListener {
                    onItemClickListener.onItemClicked(listPost[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPost.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPost.size

    override fun onBindViewHolder(holder: AdapterPost.ListViewHolder, position: Int) {
        holder.bind(listPost[position])
    }

    interface OnItemClickListener {
        fun onItemClicked(post: Post)
    }
}
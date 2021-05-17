package com.dicoding.academies.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.academies.R
import com.dicoding.academies.data.source.local.entity.TvEntity
import com.dicoding.academies.databinding.ItemsTvBinding
import com.dicoding.academies.ui.detail.DetailTvActivity
import com.dicoding.academies.utils.Constant.Companion.POSTER_BASE_URL
import java.util.*

class TvAdapter(private val callback: TvFragmentCallback) : RecyclerView.Adapter<TvAdapter.CourseViewHolder>() {
    private val listCourses = ArrayList<TvEntity>()

    fun setCourses(cours: ArrayList<TvEntity>?) {
        if (cours == null) return
        this.listCourses.clear()
        this.listCourses.addAll(cours)

        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemsTvBinding = ItemsTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemsTvBinding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = listCourses[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listCourses.size

    inner class CourseViewHolder(private val binding: ItemsTvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detailTv: TvEntity) {
            with(binding) {
                tvItemTitle.text = detailTv.originalName
                tvItemOverview.text = detailTv.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_COURSE, detailTv.id)
                    itemView.context.startActivity(intent)
                }
//                imgShare.setOnClickListener { callback.onShareClick(detailTv) }
                Glide.with(itemView.context)
                        .load(POSTER_BASE_URL + detailTv.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)
            }
        }
    }
}
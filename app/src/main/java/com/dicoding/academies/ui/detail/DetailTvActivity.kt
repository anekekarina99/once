package com.dicoding.academies.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.dicoding.academies.databinding.ActivityTvDetailBinding
import com.dicoding.academies.utils.Constant
import com.dicoding.academies.viewmodel.ViewModelFactory

class DetailTvActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COURSE = "course"
    }

    private lateinit var detailMovieBinding: ActivityTvDetailBinding
    private lateinit var viewModel : DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailMovieBinding = ActivityTvDetailBinding.inflate(layoutInflater)

        setContentView( detailMovieBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getInt(EXTRA_COURSE,0)
            viewModel.id = courseId
            viewModel.getTvDetail().observe(this, {
                if(it != null){
                    detailMovieBinding.textTitle.text = it.originalName
                    detailMovieBinding.textDescription.text = it.overview
                    detailMovieBinding.textDate.text = it.firstAirDate
                    detailMovieBinding.textDesc.text = it.id.toString()

                    Glide.with(this)
                            .load(Constant.POSTER_BASE_URL + it.posterPath)
                            .transform(RoundedCorners(10))
                            .into(detailMovieBinding.imagePoster)
                }

            })

        }


    }
}

package com.dicoding.academies.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.dicoding.academies.databinding.ActivityMovieDetailBinding
import com.dicoding.academies.utils.Constant
import com.dicoding.academies.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "id"
    }

    private lateinit var detailMovieBinding: ActivityMovieDetailBinding
    private lateinit var viewModel : DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailMovieBinding = ActivityMovieDetailBinding.inflate(layoutInflater)

        setContentView( detailMovieBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getInt(EXTRA_ID,0)
            viewModel.id = courseId
            viewModel.getMovieDetail().observe(this, {
                if(it != null){
                   detailMovieBinding.textTitle.text = it.originalTitle
                   detailMovieBinding.textDescription.text = it.overview
                   detailMovieBinding.textDate.text = it.releaseDate
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
package com.dicoding.academies.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.MovieEntity

class MovieViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getMovie(): LiveData<ArrayList<MovieEntity>> = academyRepository.getMoviePopular()
}


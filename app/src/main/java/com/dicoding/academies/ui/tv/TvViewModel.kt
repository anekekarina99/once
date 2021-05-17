package com.dicoding.academies.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.DetailMovieEntity
import com.dicoding.academies.data.source.local.entity.TvEntity

class TvViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getTv(): LiveData<ArrayList<TvEntity>> = academyRepository.getTvPopular()
}


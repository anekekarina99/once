package com.dicoding.academies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.DetailMovieEntity
import com.dicoding.academies.data.source.local.entity.DetailTvEntity
import com.dicoding.academies.data.source.local.entity.TvEntity
import kotlin.properties.Delegates

class DetailViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    var id by Delegates.notNull<Int>()

    fun setSelectedCourse(id: Int) {
        this.id = id
    }

    fun getMovieDetail(): LiveData<DetailMovieEntity> = academyRepository.getMovieDetail(id)

    fun getTvDetail(): LiveData<DetailTvEntity> = academyRepository.getTvDetail(id)
}



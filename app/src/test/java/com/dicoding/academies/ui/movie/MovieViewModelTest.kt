package com.dicoding.academies.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.MovieEntity
import com.dicoding.academies.utils.DataDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<ArrayList<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(academyRepository)
    }

    @Test
    fun getTv() {
        val dummyMovie = DataDummy.generateDummyMovie()
        val courses = MutableLiveData<ArrayList<MovieEntity>>()
        courses.value = dummyMovie

        Mockito.`when`(academyRepository.getMoviePopular()).thenReturn(courses)
        val courseEntities = viewModel.getMovie().value
        Mockito.verify(academyRepository).getMoviePopular()
        Assert.assertNotNull(courseEntities)
        Assert.assertEquals(dummyMovie.size, courseEntities?.size)

        viewModel.getMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }
}
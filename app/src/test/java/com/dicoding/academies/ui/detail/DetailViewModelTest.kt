package com.dicoding.academies.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.DetailMovieEntity
import com.dicoding.academies.data.source.local.entity.TvEntity
import com.dicoding.academies.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyDetailMovie()
    private val mId = dummyMovie.id
    private val dummyModules = DataDummy.generateDummyDetailMovie(mId)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var academyRepository= mock(AcademyRepository ::class.java)

    @Mock
    private lateinit var detailMovieObserver: Observer<DetailMovieEntity>


    @Before
    fun setUp() {
        viewModel = DetailViewModel(academyRepository)
        viewModel.id = mId

    }

    @Test
    fun getMovieDetail() {
        val course = MutableLiveData<ArrayList<DetailMovieEntity>>()
        course.value = dummyModules

        `when`(academyRepository.getMovieDetail(mId)).thenReturn(course)
        val courseEntity = viewModel.getMovieDetail().value as DetailMovieEntity
        verify(academyRepository).getMovieDetail(mId)
        assertNotNull(courseEntity)
        assertEquals(dummyModules.id, courseEntity.id)

        viewModel.getMovieDetail().observeForever(detailMovieObserver)
        verify(detailMovieObserver).onChanged(dummyModules)
    }

    @Test
    fun getModules() {
        val module = MutableLiveData<ArrayList<DetailMovieEntity>>()
        module.value = dummyModules

        `when`(academyRepository.getMovieDetail(mId)).thenReturn(module)
        val moduleEntities = viewModel.getModules().value
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities?.size)

        viewModel.getModules().observeForever(modulesObserver)
        verify(modulesObserver).onChanged(dummyModules)
    }
}
package com.dicoding.academies.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.TvEntity
import com.dicoding.academies.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {
    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<ArrayList<TvEntity>>

    @Before
    fun setUp() {
        viewModel = TvViewModel(academyRepository)
    }

    @Test
    fun getTv() {
        val dummyTv = DataDummy.generateDummyTv()
        val courses = MutableLiveData<ArrayList<TvEntity>>()
        courses.value = dummyTv

        `when`(academyRepository.getTvPopular()).thenReturn(courses)
        val courseEntities = viewModel.getTv().value
        verify(academyRepository).getTvPopular()
        assertNotNull(courseEntities)
        assertEquals(dummyTv.size, courseEntities?.size)

        viewModel.getTv().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}
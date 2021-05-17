package com.dicoding.academies.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.academies.data.source.remote.RemoteDataSource
import com.dicoding.academies.utils.DataDummy
import com.dicoding.academies.utils.LiveDataTestUtil

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer

class AcademyRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remote)

    private val movieResponses = DataDummy.generateDummyMovie()
    private val mId = movieResponses[0].id
    private val movieDetailResponses = DataDummy.generateDummyDetailMovie()

    private val tvResponses = DataDummy.generateDummyTv()
    private val tId = tvResponses[0].id
    private val tvDetailResponses = DataDummy.generateDummyTvDetail()

    @Test
    fun getMoviePopular() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovie)
                    .onLoadMovie(movieResponses)
            null
        }.`when`(remote).getMoviePopular(any())
        val movieEntities = LiveDataTestUtil.getValue(academyRepository.getMoviePopular())
        verify(remote).getMoviePopular(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback)
                    .onLoadDetailMovie(movieDetailResponses)
            null
        }.`when`(remote).getMovieDetails(any(),eq(mId) )

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getMovieDetail(mId))

        verify(remote).getMovieDetails(any(),eq(mId))

        assertNotNull(courseEntities)
        assertEquals(movieDetailResponses.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getTvPopular() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTv)
                    .onLoadTv(tvResponses)
            null
        }.`when`(remote).getTvPopular(any())
        val movieEntities = LiveDataTestUtil.getValue(academyRepository.getTvPopular())
        verify(remote).getTvPopular(any())
        assertNotNull(movieEntities)
        assertEquals(movieEntities.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getTvDetail() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvDetailCallback)
                    .onLoadDetailTv(tvDetailResponses)
            null
        }.`when`(remote).getMovieDetails(any(),eq(tId) )

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getTvDetail(tId))

        verify(remote).getTvDetails(any(),eq(tId))

        assertNotNull(courseEntities)
        assertEquals(tvDetailResponses.size.toLong(), courseEntities.size.toLong())
    }

}
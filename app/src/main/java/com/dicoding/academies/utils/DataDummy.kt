package com.dicoding.academies.utils

import com.dicoding.academies.data.source.local.entity.DetailMovieEntity
import com.dicoding.academies.data.source.local.entity.DetailTvEntity
import com.dicoding.academies.data.source.local.entity.MovieEntity
import com.dicoding.academies.data.source.local.entity.TvEntity

import java.util.ArrayList

object DataDummy {

    fun generateDummyMovie(): ArrayList<MovieEntity> {

        val courses = ArrayList<MovieEntity>()

        courses.add(MovieEntity(460465,
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "Mortal Kombat",
                "2021-04-07",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
        ))

        return courses
    }

    fun generateDummyDetailMovie(): DetailMovieEntity {

        return DetailMovieEntity(460465,
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "Mortal Kombat",
                "2021-04-07",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
        )

    }

    fun generateDummyTv(): ArrayList<TvEntity> {

        val courses = ArrayList<TvEntity>()

        courses.add(TvEntity(88396,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "The Falcon and the Winter Soldier",
                "2021-03-19",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
        ))

        return courses
    }

    fun generateDummyTvDetail(): DetailTvEntity {

        return DetailTvEntity(88396,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "The Falcon and the Winter Soldier",
                "2021-03-19",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
        )

    }

}

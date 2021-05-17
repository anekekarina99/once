package com.dicoding.academies.ui.tv

import com.dicoding.academies.data.source.local.entity.TvEntity

interface TvFragmentCallback {
    fun onShareClick(detailTv: TvEntity)
}


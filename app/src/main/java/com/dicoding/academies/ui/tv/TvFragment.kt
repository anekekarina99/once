package com.dicoding.academies.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academies.data.source.local.entity.TvEntity
import com.dicoding.academies.databinding.FragmentTvBinding
import com.dicoding.academies.viewmodel.ViewModelFactory


class TvFragment : Fragment(), TvFragmentCallback {

    lateinit var fragmentTvBinding: FragmentTvBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        fragmentTvBinding = FragmentTvBinding.inflate(inflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]

            val adapter = TvAdapter(this)

            fragmentTvBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTv().observe(viewLifecycleOwner, { courses ->
                fragmentTvBinding.progressBar.visibility = View.GONE
                adapter.setCourses(courses)
                adapter.notifyDataSetChanged()
            })

            with(fragmentTvBinding.rvBookmark) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    override fun onShareClick(detailTv: TvEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder.from(requireActivity()).apply {
                setType(mimeType)
                setChooserTitle("Bagikan aplikasi ini sekarang.")
                setText("Segera tonton ${detailTv.originalName} di tmdbb")
            }.startChooser()
        }
    }


}
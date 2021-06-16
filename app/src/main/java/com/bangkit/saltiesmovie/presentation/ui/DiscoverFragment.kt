package com.bangkit.saltiesmovie.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bangkit.saltiesmovie.R
import com.bangkit.saltiesmovie.core.domainlayer.repository.SaltiesRepository
import com.bangkit.saltiesmovie.core.util.FieldInjection
import com.bangkit.saltiesmovie.databinding.FragmentDetailBinding
import com.bangkit.saltiesmovie.databinding.FragmentDiscoverBinding
import com.bangkit.saltiesmovie.presentation.MyPagingAdapter
import com.bangkit.saltiesmovie.presentation.viewmodel.DetailFragmentVM
import com.bangkit.saltiesmovie.presentation.viewmodel.DiscoverFragmentVM
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DiscoverFragment : Fragment() {

    val myViewModel: DiscoverFragmentVM by viewModel()

    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.favoriteFragment)
        }

        val myAdapter = MyPagingAdapter.MyPagingAdapter(
            MyPagingAdapter.MyPagingAdapter.myItemClickListener{
                val bundle = Bundle()
                bundle.putInt("MovieId", it.id!!)

                findNavController().navigate(R.id.detailFragment, bundle)
            }
        )
        binding.listDisover.adapter = myAdapter
        binding.listDisover.layoutManager = StaggeredGridLayoutManager( 3, StaggeredGridLayoutManager.VERTICAL)
        viewLifecycleOwner.lifecycleScope.launch {
            myViewModel.pagingData.collectLatest {
                myAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding.listDisover.adapter = null
        _binding = null
    }

}
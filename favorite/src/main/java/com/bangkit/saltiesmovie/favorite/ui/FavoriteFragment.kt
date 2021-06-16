package com.bangkit.saltiesmovie.favorite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bangkit.saltiesmovie.R
import com.bangkit.saltiesmovie.favorite.viewmodel.FavoriteFragmentVM
import com.bangkit.saltiesmovie.favorite.di.StorageModul
import com.bangkit.saltiesmovie.favorite.databinding.FragmentFavoriteBinding
import com.bangkit.saltiesmovie.presentation.MyPagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    val myViewModel: FavoriteFragmentVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(StorageModul.favoriteModul)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
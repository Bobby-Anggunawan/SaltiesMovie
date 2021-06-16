package com.bangkit.saltiesmovie.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bangkit.saltiesmovie.R
import com.bangkit.saltiesmovie.core.domainlayer.repository.SaltiesRepository
import com.bangkit.saltiesmovie.core.util.FieldInjection
import com.bangkit.saltiesmovie.databinding.FragmentDetailBinding
import com.bangkit.saltiesmovie.presentation.viewmodel.DetailFragmentVM
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : Fragment() {
    val myViewModel: DetailFragmentVM by viewModel()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var movieID: Int = 337404
        val bundle = this.arguments
        if (bundle != null) {
            movieID = bundle.getInt("MovieId", 337404)
        }


        viewLifecycleOwner.lifecycleScope.launch {
            myViewModel.getData(movieID).collect {
                binding.judulFilm.text = it.original_title
                binding.tagLine.text = it.tagline
                binding.tangalRilis.text = it.release_date
                binding.status.text = it.status
                binding.rating.rating = it.vote_average.toFloat()
                binding.overview.text = it.overview

                Glide.with(requireContext()).load("https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces"+it.backdrop_path).into(binding.poster)
                Glide.with(requireContext()).load("https://www.themoviedb.org/t/p/w220_and_h330_face"+it.poster_path).into(binding.background)


                //set up favorite
                myViewModel.getFavorit(it).observe(viewLifecycleOwner, Observer { isFavorite->
                    if(isFavorite == false){
                        binding.addFavorite.setImageResource(R.drawable.ic_favorite_white_18dp)
                    }
                    else{
                        binding.addFavorite.setImageResource(R.drawable.ic_favorite_red_18dp)
                    }
                })

                binding.addFavorite.setOnClickListener { aView->
                    myViewModel.postFavorit(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
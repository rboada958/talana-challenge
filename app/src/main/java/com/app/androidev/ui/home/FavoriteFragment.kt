package com.app.androidev.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.androidev.databinding.FragmentFavoriteBinding
import com.app.androidev.ui.home.adapter.FavoriteAdapter
import com.app.androidev.ui.home.adapter.FeedAdapter
import com.app.androidev.ui.home.mvvm.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<FeedViewModel>()
    private val adapter by lazy {
        FavoriteAdapter(mutableListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFavoriteList()

        binding.recyclerFavorite.adapter = adapter

        viewModel.favoriteList.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { list ->
                adapter.addItems(list)
            }
        }

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
package com.app.androidev.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.androidev.R
import com.app.androidev.app.model.favorite.ResponseFavorite
import com.app.androidev.databinding.FragmentFeedDetailsBinding
import com.app.androidev.ui.home.mvvm.FeedViewModel
import com.app.androidev.utils.base.gone
import com.app.androidev.utils.base.loadRect
import com.app.androidev.utils.base.parseDate
import com.app.androidev.utils.base.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedDetailsFragment : Fragment() {

    private var _binding: FragmentFeedDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<FeedViewModel>()
    private val args by navArgs<FeedDetailsFragmentArgs>()
    private lateinit var result: ResponseFavorite

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showFeedDetails()

        viewModel.favoriteUiState.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { uiState ->
                when (uiState) {
                    is FeedViewModel.FavoriteUiState.ShowLoading -> {
                        binding.progress.visible()
                    }
                    is FeedViewModel.FavoriteUiState.Error -> {
                        binding.progress.gone()
                        Toast.makeText(requireContext(), uiState.msg, Toast.LENGTH_SHORT).show()
                    }
                    is FeedViewModel.FavoriteUiState.Success -> {
                        this@FeedDetailsFragment.result = uiState.result
                        binding.progress.gone()
                        binding.btnFavorite.setImageResource(R.drawable.ic_filled_star)
                        viewModel.saveFavorite(args.feed)
                    }
                }
            }
        }

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun showFeedDetails() {
        binding.avatar.loadRect(args.feed.image)
        binding.title.text = args.feed.title
        binding.date.parseDate(args.feed.date!!)
        binding.description.text = args.feed.description

        binding.btnFavorite.setOnClickListener {
            viewModel.setFavorite(args.feed.id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
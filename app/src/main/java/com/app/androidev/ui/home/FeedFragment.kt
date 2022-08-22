package com.app.androidev.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.androidev.R
import com.app.androidev.app.model.feeds.FeedItem
import com.app.androidev.databinding.FragmentFeedBinding
import com.app.androidev.ui.home.mvvm.FeedViewModel
import com.app.androidev.ui.home.adapter.FeedAdapter
import com.app.androidev.utils.base.gone
import com.app.androidev.utils.base.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(), FeedAdapter.OnFeedClickListener {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<FeedViewModel>()
    private val adapter by lazy {
        FeedAdapter(mutableListOf(), this)
    }

    private lateinit var result: List<FeedItem?>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFeeds()

        binding.recyclerFeed.adapter = adapter

        viewModel.feedUiState.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { uiState ->
                when (uiState) {
                    is FeedViewModel.FeedUiState.ShowLoading -> {
                        binding.progress.visible()
                    }
                    is FeedViewModel.FeedUiState.Error -> {
                        binding.progress.gone()
                        Toast.makeText(requireContext(), uiState.msg, Toast.LENGTH_SHORT).show()
                    }
                    is FeedViewModel.FeedUiState.Success -> {
                        this@FeedFragment.result = uiState.result!!
                        binding.progress.gone()
                        adapter.addItems(result)
                    }
                }
            }
        }

        binding.back.setOnClickListener {
            activity!!.finish()
        }

        binding.favorite.setOnClickListener {
            findNavController().navigate(R.id.favoriteFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFeedClicked(item: FeedItem) {
        findNavController().navigate(R.id.feedDetailsFragment, bundleOf("feed" to item))
    }
}
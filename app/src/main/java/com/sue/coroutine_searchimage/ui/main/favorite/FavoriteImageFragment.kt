package com.sue.coroutine_searchimage.ui.main.favorite

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.sue.coroutine_searchimage.databinding.FragmentFavoriteImageBinding
import com.sue.coroutine_searchimage.domain.model.Image
import com.sue.coroutine_searchimage.ui.main.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class FavoriteImageFragment: BaseFragment<FavoriteImageViewModel, FragmentFavoriteImageBinding>() {
    override val viewModel: FavoriteImageViewModel by viewModels()

    override fun getViewBinding(): FragmentFavoriteImageBinding = FragmentFavoriteImageBinding.inflate(layoutInflater)

    private val adapter by lazy {
        FavoriteImageAdapter(
            onClickImage = { image ->
                viewModel.deleteFavoriteImage(image)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() = with(binding){
        rvImage.adapter = adapter
    }

    override fun observeData() = viewModel.state.observe(viewLifecycleOwner){ state ->
        when(state) {
            is FavoriteImageState.Success -> handleSuccess(state.images)
        }
    }

    private fun handleSuccess(images: List<Image>) {
        binding.tvEmptyList.isVisible = images.isEmpty()
        adapter.submitList(images)
    }
}
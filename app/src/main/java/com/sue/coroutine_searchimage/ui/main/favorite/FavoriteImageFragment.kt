package com.sue.coroutine_searchimage.ui.main.favorite

import androidx.fragment.app.viewModels
import com.sue.coroutine_searchimage.databinding.FragmentFavoriteImageBinding
import com.sue.coroutine_searchimage.ui.main.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class FavoriteImageFragment: BaseFragment<FavoriteImageViewModel, FragmentFavoriteImageBinding>() {
    override val viewModel: FavoriteImageViewModel by viewModels()

    override fun getViewBinding(): FragmentFavoriteImageBinding = FragmentFavoriteImageBinding.inflate(layoutInflater)

    override fun observeData() {

    }
}
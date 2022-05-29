package com.sue.coroutine_searchimage.ui.main.search

import androidx.fragment.app.viewModels
import com.sue.coroutine_searchimage.databinding.FragmentSearchImageBinding
import com.sue.coroutine_searchimage.ui.main.BaseFragment

internal class SearchImageFragment: BaseFragment<SearchImageViewModel, FragmentSearchImageBinding>() {
    override val viewModel: SearchImageViewModel by viewModels()

    override fun getViewBinding(): FragmentSearchImageBinding = FragmentSearchImageBinding.inflate(layoutInflater)

    override fun observeData() { }
}
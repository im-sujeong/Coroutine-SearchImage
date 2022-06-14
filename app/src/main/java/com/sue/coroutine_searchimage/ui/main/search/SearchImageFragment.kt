package com.sue.coroutine_searchimage.ui.main.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.sue.coroutine_searchimage.databinding.FragmentSearchImageBinding
import com.sue.coroutine_searchimage.domain.model.Image
import com.sue.coroutine_searchimage.ui.main.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.HttpException

@AndroidEntryPoint
internal class SearchImageFragment: BaseFragment<SearchImageViewModel, FragmentSearchImageBinding>() {
    override val viewModel: SearchImageViewModel by viewModels()

    override fun getViewBinding(): FragmentSearchImageBinding = FragmentSearchImageBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private val adapter by lazy {
        SearchImageAdapter(onClickImage = { image ->
            viewModel.favoriteImage(image)
        })
    }

    private fun initViews() = with(binding) {
        etSearch.setOnEditorActionListener { textView, keyCode, _ ->
            when(keyCode) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.handleQuery(textView?.text.toString())
                    return@setOnEditorActionListener false
                }
                else -> {
                    return@setOnEditorActionListener true
                }
            }
        }

        rvImage.adapter = adapter

        adapter.addLoadStateListener {
            when(it.refresh) {
                is LoadState.Error -> {
                    //TODO 페이징 사용시 에러 처리는 이곳에서..
                    val error = (it.refresh as LoadState.Error).error
                }
            }

            if(it.append.endOfPaginationReached ) {
                tvEmptyList.isVisible = adapter.itemCount == 0
            }else {
                tvEmptyList.isVisible = false
            }
        }
    }

    override fun observeData() = viewModel.state.observe(viewLifecycleOwner) { state ->
        when(state) {
            is SearchImageState.Success -> handleSuccess(state.images)
        }
    }

    private fun handleSuccess(images: PagingData<Image>) {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitData(images)
        }
    }
}
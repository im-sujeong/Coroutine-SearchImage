package com.sue.coroutine_searchimage.ui.main.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import com.sue.coroutine_searchimage.databinding.FragmentSearchImageBinding
import com.sue.coroutine_searchimage.ui.main.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class SearchImageFragment: BaseFragment<SearchImageViewModel, FragmentSearchImageBinding>() {
    override val viewModel: SearchImageViewModel by viewModels()

    override fun getViewBinding(): FragmentSearchImageBinding = FragmentSearchImageBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews() = with(binding) {
        searchEdittext.setOnEditorActionListener { textView, keyCode, keyEvent ->
            when(keyCode) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    return@setOnEditorActionListener false
                }
                else -> {
                    return@setOnEditorActionListener true
                }
            }
        }
    }

    override fun observeData() { }
}
package com.tt.kotlintryout.posts

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.tt.kotlintryout.R
import com.tt.kotlintryout.base.BaseActivity
import com.tt.kotlintryout.base.adapter.ItemClickListener
import com.tt.kotlintryout.comments.CommentsActivity
import com.tt.kotlintryout.extensions.setup
import com.tt.kotlintryout.extensions.showBackArrow
import kotlinx.android.synthetic.main.activity_simple_content.*
import kotlinx.android.synthetic.main.include_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsActivity : BaseActivity(), ItemClickListener<PostItem> {

    private val viewModel: PostsViewModel by viewModel()
    private val adapter = PostsAdapter(this, View.OnClickListener { viewModel.loadPosts() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_content)

        setupToolbar()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupToolbar() {
        toolbar.setTitle(R.string.posts_title)
        toolbar.setOnClickListener { viewModel.loadPosts() }
        toolbar.showBackArrow(this)
    }

    private fun setupRecyclerView() {
        recyclerView.setup(adapter = adapter)
    }

    private fun observeViewModel() {
        viewModel.getPosts().observe(this, Observer { state -> adapter.updateState(state) })
    }

    override fun onItemClicked(item: PostItem) {
        startActivity(CommentsActivity.createForPost(this, item))
    }
}
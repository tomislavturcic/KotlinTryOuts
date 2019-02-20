package com.tt.kotlintryout.comments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.tt.kotlintryout.R
import com.tt.kotlintryout.base.BaseActivity
import com.tt.kotlintryout.base.adapter.ItemClickListener
import com.tt.kotlintryout.base.state.isSuccess
import com.tt.kotlintryout.extensions.setup
import com.tt.kotlintryout.extensions.showBackArrow
import com.tt.kotlintryout.extensions.showToast
import com.tt.kotlintryout.posts.PostItem
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.include_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CommentsActivity : BaseActivity(), ItemClickListener<CommentItem> {

    companion object {

        private const val EXTRA_POST_ID = "postId"
        private const val EXTRA_POST_TITLE = "postTitle"
        private const val EXTRA_POST_BODY = "postBody"

        @JvmStatic
        fun createForPost(context: Context, postItem: PostItem): Intent {
            val intent = Intent(context, CommentsActivity::class.java)
            intent.putExtra(EXTRA_POST_ID, postItem.id)
            intent.putExtra(EXTRA_POST_TITLE, postItem.title)
            intent.putExtra(EXTRA_POST_BODY, postItem.body)
            return intent
        }
    }

    private val viewModel: CommentsViewModel by viewModel { parametersOf(getPostId()) }
    private val adapter = CommentsAdapter(this, View.OnClickListener { viewModel.loadComments() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        setupToolbar()
        setupRecyclerView()
        setupFab()
        observeViewModel()
    }

    private fun getPostId(): Int {
        return intent.getIntExtra(EXTRA_POST_ID, -1)
    }

    private fun setupToolbar() {
        toolbar.setTitle(R.string.comments_title)
        toolbar.showBackArrow(this)
    }

    private fun setupRecyclerView() {
        recyclerView.setup(adapter = adapter)
    }

    private fun setupFab() {
        fab.hide()
        fab.setOnClickListener { AddCommentDialog().show(supportFragmentManager) }
    }

    private fun observeViewModel() {
        viewModel.getComments().observe(this, Observer { state ->
            adapter.updateState(state)
            if (state.isSuccess()) {
                fab.show()
            }
        })
        viewModel.getAddCommentErrorEvent().observe(this, Observer { showToast(R.string.comments_add_new_error) })
    }

    override fun onItemClicked(item: CommentItem) {
        // Work in progress...
    }
}
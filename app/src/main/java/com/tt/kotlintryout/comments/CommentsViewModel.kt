package com.tt.kotlintryout.comments

import androidx.lifecycle.LiveData
import com.tt.kotlintryout.base.BaseViewModel
import com.tt.kotlintryout.base.SingleLiveEvent
import com.tt.kotlintryout.base.state.StateLiveData
import com.tt.kotlintryout.base.state.ViewState
import com.tt.kotlintryout.extensions.subOnIoObserveOnMain
import com.tt.kotlintryout.extensions.toCommentItems
import com.tt.kotlintryout.extensions.toViewState
import io.reactivex.Single

class CommentsViewModel(
    private val postId: Int,
    private val repository: CommentsRepository
) : BaseViewModel() {

    private val commentsState = StateLiveData<List<CommentItem>>()
    private val addCommentErrorEvent = SingleLiveEvent<Throwable>()

    init {
        loadComments()
    }

    fun loadComments() {
        val loadCommentsRequest = getCommentsMapped()
        val disposable = loadCommentsRequest
            .toViewState()
            .subscribe { state -> commentsState.value = state }
        addSubscription(disposable)
    }

    private fun getCommentsMapped(): Single<List<CommentItem>> {
        return repository.getCommentsForPost(postId)
            .map { commentsResponse -> commentsResponse.toCommentItems() }
    }

    fun addComment(text: String) {
        if (text.isNotBlank()) {
            addSubscription(
                repository.addUserCommentForPost(postId, text)
                    .andThen(getCommentsMapped())
                    .subOnIoObserveOnMain()
                    .subscribe({ commentItems -> commentsState.setData(commentItems) },
                        { throwable -> addCommentErrorEvent.value = throwable })
            )
        }
    }

    fun getComments() = commentsState as LiveData<ViewState<List<CommentItem>>>

    fun getAddCommentErrorEvent() = addCommentErrorEvent as LiveData<Throwable>

}
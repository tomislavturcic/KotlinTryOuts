package com.tt.kotlintryout.comments

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.tt.kotlintryout.R
import kotlinx.android.synthetic.main.dialog_add_comment.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddCommentDialog : DialogFragment() {

    private val viewModel by sharedViewModel<CommentsViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())

        val root = View.inflate(requireActivity(), R.layout.dialog_add_comment, null)
        val editText = root.editText

        builder.setView(root)
            .setTitle(R.string.comments_add_new_dialog_title)
            .setPositiveButton(R.string.generic_ok) { dialog, which -> viewModel.addComment(editText.text.toString()) }
            .setNegativeButton(R.string.generic_cancel, null)

        return builder.create()
    }

    fun show(fm: FragmentManager) {
        show(fm, "AddCommentDialog")
    }
}
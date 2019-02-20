package com.tt.kotlintryout.main

import android.os.Bundle
import com.tt.kotlintryout.R
import com.tt.kotlintryout.base.BaseActivity
import com.tt.kotlintryout.base.adapter.ItemClickListener
import com.tt.kotlintryout.extensions.setup
import com.tt.kotlintryout.extensions.showToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.*

class MainActivity : BaseActivity(), ItemClickListener<MainListItem> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar(){
        toolbar.setTitle(R.string.main_title)
    }

    private fun setupRecyclerView(){
        val adapter = MainAdapter(this)
        adapter.updateItems(MainListItem.values().asList())
        mainRecyclerView.setup(adapter = adapter)
    }

    override fun onItemClicked(item: MainListItem) {
        val intent = item.createIntent(this)
        if(intent != null){
            startActivity(intent)
        } else {
            showToast(R.string.generic_work_in_progress)
        }
    }

}

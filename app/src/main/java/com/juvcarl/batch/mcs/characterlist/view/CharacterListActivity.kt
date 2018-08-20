package com.juvcarl.batch.mcs.characterlist.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.juvcarl.batch.mcs.characterlist.App
import com.juvcarl.batch.mcs.characterlist.BuildConfig.SEARCH_CONSTANT
import com.juvcarl.batch.mcs.characterlist.R
import com.juvcarl.batch.mcs.characterlist.repository.Resource
import com.juvcarl.batch.mcs.characterlist.repository.model.RelatedTopic
import com.juvcarl.batch.mcs.characterlist.repository.model.Result
import com.juvcarl.batch.mcs.characterlist.viewmodel.CharacterViewModel
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [CharacterDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class CharacterListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    lateinit var characterViewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (item_detail_container != null) {
            twoPane = true
        }

        characterViewModel = CharacterViewModel.create(this)
        App.appComponent.inject(characterViewModel)

        characterViewModel.searchResult.observe(this,Observer<Resource<Result>> {
            resource ->
            if(resource!=null){
                when(resource.status){
                    Resource.Status.SUCCESS -> {
                        if(resource.data is Result){
                            setupRecyclerView(item_list,resource.data.relatedTopics)
                        }
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(this,"Error: "+resource.exception?.message,Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(this,"Error: No Values",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        characterViewModel.search(SEARCH_CONSTANT)

    }

    private fun setupRecyclerView(recyclerView: RecyclerView, relatedTopics: List<RelatedTopic>?) {
        recyclerView.adapter = CharacterRecyclerViewAdapter(this, relatedTopics, twoPane)
    }


}

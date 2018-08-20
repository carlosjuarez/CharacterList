package com.juvcarl.batch.mcs.characterlist.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juvcarl.batch.mcs.characterlist.R
import com.juvcarl.batch.mcs.characterlist.repository.model.RelatedTopic
import kotlinx.android.synthetic.main.activity_item_detail.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [CharacterListActivity]
 * in two-pane mode (on tablets) or a [CharacterDetailActivity]
 * on handsets.
 */
class CharacterDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    //private var item: DummyContent.DummyItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(CHARACTER)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                //item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
                val character = it.getParcelable<RelatedTopic?>(CHARACTER)
                activity?.toolbar_layout?.title = character.getCharacterName()//item?.content
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the dummy content as text in a TextView.
        /*item?.let {
            rootView.item_detail.text = it.details
        }*/

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val CHARACTER = "Characters"
    }
}

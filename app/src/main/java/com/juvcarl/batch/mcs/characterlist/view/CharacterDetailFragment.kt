package com.juvcarl.batch.mcs.characterlist.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juvcarl.batch.mcs.characterlist.R
import com.juvcarl.batch.mcs.characterlist.repository.model.RelatedTopic
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [CharacterListActivity]
 * in two-pane mode (on tablets) or a [CharacterDetailActivity]
 * on handsets.
 */
class CharacterDetailFragment : Fragment() {

    private var character: RelatedTopic? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(CHARACTER)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                //item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
                character = it.getParcelable<RelatedTopic?>(CHARACTER)
                activity?.toolbar_layout?.title = character.getCharacterName()//item?.content
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        character?.icon?.url?.let { rootView.image_character.setImageFromUrl(it) }
        rootView.character_detail_name.text = character.getCharacterName()
        rootView.character_detail_description.text = character.getCharacterDescription()

        return rootView
    }

    companion object {
        const val CHARACTER = "Characters"
    }
}

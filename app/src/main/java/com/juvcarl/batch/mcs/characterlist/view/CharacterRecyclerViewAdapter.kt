package com.juvcarl.batch.mcs.characterlist.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.juvcarl.batch.mcs.characterlist.R
import com.juvcarl.batch.mcs.characterlist.repository.model.RelatedTopic
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_content_list.view.*

class CharacterRecyclerViewAdapter(private val parentActivity: CharacterListActivity,
                                   private val values: List<RelatedTopic>?,
                                   private val twoPane: Boolean) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val GRID_VIEW_ITEM = 1
        const val LINEAR_VIEW_ITEM = 2
    }

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as RelatedTopic?
            if (twoPane) {
                val fragment = CharacterDetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(CharacterDetailFragment.CHARACTER, item)
                    }
                }
                parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
            } else {
                val intent = Intent(v.context, CharacterDetailActivity::class.java).apply {
                    putExtra(CharacterDetailFragment.CHARACTER, item)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (twoPane) {
            return LINEAR_VIEW_ITEM
        } else {
            return GRID_VIEW_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == LINEAR_VIEW_ITEM) {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_content_list, parent, false)
            return ViewHolderList(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_content_grid, parent, false)
            return ViewHolderGrid(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item : RelatedTopic? = values?.get(position)
        when (holder.itemViewType) {
            LINEAR_VIEW_ITEM -> {
                (holder as ViewHolderList).characterNameView.text = item.getCharacterName()

            }
            GRID_VIEW_ITEM -> {
                (holder as ViewHolderGrid).characterNameView.text = item.getCharacterName()
                item?.icon?.url?.let { holder.characterIcon.setImageFromUrl(it) }
            }
        }


        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values?.size ?: 0

    inner class ViewHolderList(view: View) : RecyclerView.ViewHolder(view) {
        val characterNameView: TextView = view.character_name
    }

    inner class ViewHolderGrid(view: View) : RecyclerView.ViewHolder(view) {
        val characterIcon: ImageView = view.findViewById(R.id.character_icon)
        val characterNameView: TextView = view.character_name
    }
}

fun ImageView.setImageFromUrl(url: String){
    if(!url.isEmpty()) {
        Picasso.get().load(url)
                .error(R.drawable.ic_error_outline_black_24dp)
                .placeholder(R.drawable.ic_file_download_black_24dp)
                .into(this)
    }else{
        this.setImageDrawable(this.context.getDrawable(R.drawable.ic_error_outline_black_24dp))
    }
}

fun RelatedTopic?.getCharacterName(): String {
    return this?.text?.indexOf(" - ")?.let { text?.substring(0, it) } ?: ""
}

fun RelatedTopic?.getCharacterDescription(): String? {
    return this?.text?.indexOf("-")?.let { text?.substring(it + 1)?.trim() } ?: ""
}

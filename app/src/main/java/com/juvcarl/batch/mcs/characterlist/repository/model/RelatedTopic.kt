package com.juvcarl.batch.mcs.characterlist.repository.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RelatedTopic(val icon: Icon?,val text: String?) : Parcelable {}
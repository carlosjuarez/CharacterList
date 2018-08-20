package com.juvcarl.batch.mcs.characterlist.repository.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Icon(val url: String?) : Parcelable {}
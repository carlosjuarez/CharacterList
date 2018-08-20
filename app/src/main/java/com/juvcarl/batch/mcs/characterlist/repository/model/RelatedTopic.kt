package com.juvcarl.batch.mcs.characterlist.repository.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RelatedTopic() : Parcelable {

    @SerializedName("Icon")
    @Expose
    var icon: Icon? = null
    @SerializedName("FirstURL")
    @Expose
    var firstURL: String? = null
    @SerializedName("Text")
    @Expose
    var text: String? = null
    @SerializedName("Result")
    @Expose
    var result: String? = null

    constructor(parcel: Parcel) : this() {
        icon = parcel.readParcelable(Icon::class.java.classLoader)
        firstURL = parcel.readString()
        text = parcel.readString()
        result = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(icon, flags)
        parcel.writeString(firstURL)
        parcel.writeString(text)
        parcel.writeString(result)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RelatedTopic> {
        override fun createFromParcel(parcel: Parcel): RelatedTopic {
            return RelatedTopic(parcel)
        }

        override fun newArray(size: Int): Array<RelatedTopic?> {
            return arrayOfNulls(size)
        }
    }

}
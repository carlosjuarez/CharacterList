package com.juvcarl.batch.mcs.characterlist.repository.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Icon() : Parcelable {

    @SerializedName("Height")
    @Expose
    var height: String? = null
    @SerializedName("URL")
    @Expose
    var url: String? = null
    @SerializedName("Width")
    @Expose
    var width: String? = null

    constructor(parcel: Parcel) : this() {
        height = parcel.readString()
        url = parcel.readString()
        width = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(height)
        parcel.writeString(url)
        parcel.writeString(width)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Icon> {
        override fun createFromParcel(parcel: Parcel): Icon {
            return Icon(parcel)
        }

        override fun newArray(size: Int): Array<Icon?> {
            return arrayOfNulls(size)
        }
    }

}
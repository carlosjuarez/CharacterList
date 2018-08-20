package com.juvcarl.batch.mcs.characterlist.repository.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Maintainer {

    @SerializedName("github")
    @Expose
    var github: String? = null

}
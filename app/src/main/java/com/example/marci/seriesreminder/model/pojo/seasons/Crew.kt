package com.example.marci.seriesreminder.model.pojo.seasons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Crew(
    var id: Int? = null,
    var job: String? = null,
    var name: String? = null,
    var gender: Int? = null,
    var department: String? = null,
    @SerializedName("credit_id")
    @Expose
    var creditId: String? = null,
    @SerializedName("profile_path")
    @Expose
    var profilePath: Any? = null
)
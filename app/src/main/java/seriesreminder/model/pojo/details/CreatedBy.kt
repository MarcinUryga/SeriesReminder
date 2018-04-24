package seriesreminder.model.pojo.details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CreatedBy(
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("profile_path")
    @Expose
    var profilePath: Any? = null
)

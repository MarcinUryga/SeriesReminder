package seriesreminder.model.pojo.details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null
)
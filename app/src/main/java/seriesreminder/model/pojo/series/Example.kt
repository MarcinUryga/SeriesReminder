package seriesreminder.model.pojo.series

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Example(
    var page: Int? = null,
    var results: List<Result>? = null,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
)
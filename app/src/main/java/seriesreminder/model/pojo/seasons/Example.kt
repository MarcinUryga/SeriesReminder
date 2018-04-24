package seriesreminder.model.pojo.seasons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Example(
    var id: Int? = null,
    var name: String? = null,
    var overview: String? = null,
    var episodes: List<Episode>? = null,
    @SerializedName("_id")
    @Expose
    var _id: String? = null,
    @SerializedName("air_date")
    @Expose
    var airDate: String? = null,
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,
    @SerializedName("season_number")
    @Expose
    var seasonNumber: Int? = null
)
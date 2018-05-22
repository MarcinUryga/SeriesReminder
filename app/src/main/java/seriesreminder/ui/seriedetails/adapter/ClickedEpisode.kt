package seriesreminder.ui.seriedetails.adapter

import android.util.Pair
import android.view.View

/**
 ** Created by marci on 2018-04-17.
 */
class ClickedEpisode(
    val episodeId: Int,
    val imageOption: Pair<View, String>,
    val titleOption: Pair<View, String>,
    val airDataOption: Pair<View, String>
)
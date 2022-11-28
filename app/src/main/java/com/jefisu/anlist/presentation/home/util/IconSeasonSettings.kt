package com.jefisu.anlist.presentation.home.util

import androidx.annotation.DrawableRes
import com.jefisu.anlist.R

sealed class IconSeasonSettings(@DrawableRes val icon: Int, val title: String) {
    object Fall : IconSeasonSettings(R.drawable.ic_leaf, "Fall")
    object Winter : IconSeasonSettings(R.drawable.ic_snowflake, "Winter")
    object Spring : IconSeasonSettings(R.drawable.ic_flower, "Spring")
    object Summer : IconSeasonSettings(R.drawable.ic_sun, "Summer")
    object Schedule : IconSeasonSettings(R.drawable.ic_calendar, "Schedule")
}
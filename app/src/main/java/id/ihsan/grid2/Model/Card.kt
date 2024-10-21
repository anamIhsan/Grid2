package id.ihsan.grid2.Model;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

data class Card(
    @StringRes val name: Int,
    val availableCourses: Int,
    @DrawableRes val imageRes: Int
)
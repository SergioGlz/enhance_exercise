package dev.enhance.exercise.core.repository.models

import com.google.gson.annotations.SerializedName
import java.util.*

sealed class SpaceXResponse {
    companion object {
        const val FALCON9_ID = "5e9d0d95eda69973a809d1ec"
    }
}

data class Launch (
    @SerializedName("rocket") val rocket: String,
    @SerializedName("name") val name: String,
    @SerializedName("date_utc") val date_utc: Date,
    @SerializedName("success") val success: Boolean?,
    @SerializedName("links") val links: Links
)

data class Links(
    @SerializedName("patch") val patch: Patch,
)

data class Patch(
    @SerializedName("small") val small: String
)
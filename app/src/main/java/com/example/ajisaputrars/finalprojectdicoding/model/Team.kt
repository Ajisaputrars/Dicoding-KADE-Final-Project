package com.example.ajisaputrars.submission4.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
//        @SerializedName("idTeam")
//        var teamId: String? = null,
//
//        @SerializedName("strTeam")
//        var teamName: String? = null,
//
//        @SerializedName("strTeamBadge")
//        var teamBadge: String? = null,
//
//        @SerializedName("intFormedYear")
//        var teamFormedYear: String? = null,
//
//        @SerializedName("strStadium")
//        var teamStadium: String? = null,
//
//        @SerializedName("strDescriptionEN")
//        var teamDescription: String? = null

        val id: Long?, // This id is for database table
        @SerializedName("idTeam") val idTeam: String,
        @SerializedName("strTeam") val team: String,
        @SerializedName("strTeamShort") val teamShort: String?,
        @SerializedName("strAlternate") val alternate: String?,
        @SerializedName("intFormedYear") val formedYear: String,
        @SerializedName("strSport") val sport: String,
        @SerializedName("strLeague") val league: String,
        @SerializedName("idLeague") val idLeague: String,
        @SerializedName("strDivision") val division: String?,
        @SerializedName("strManager") val manager: String,
        @SerializedName("strStadium") val stadium: String,
        @SerializedName("strKeywords") val keywords: String?,
        @SerializedName("strStadiumThumb") val stadiumThumb: String?,
        @SerializedName("strStadiumDescription") val stadiumDescription: String?,
        @SerializedName("strStadiumLocation") val stadiumLocation: String?,
        @SerializedName("intStadiumCapacity") val stadiumCapacity: String?,
        @SerializedName("strWebsite") val website: String?,
        @SerializedName("strFacebook") val facebook: String?,
        @SerializedName("strTwitter") val twitter: String?,
        @SerializedName("strInstagram") val instagram: String?,
        @SerializedName("strDescriptionEN") val descriptionEN: String?,
        @SerializedName("strGender") val gender: String,
        @SerializedName("strCountry") val country: String,
        @SerializedName("strTeamBadge") val teamBadge: String,
        @SerializedName("strTeamJersey") val teamJersey: String?,
        @SerializedName("strTeamLogo") val teamLogo: String?,
        @SerializedName("strTeamBanner") val teamBanner: String?,
        @SerializedName("strYoutube") val youtube: String?

) : Parcelable {
    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val TEAM: String = "TEAM"
        const val TEAM_SHORT: String = "TEAM_SHORT"
        const val ALTERNATE: String = "ALTERNATE"
        const val FORMED_YEAR: String = "FORMED_YEAR"
        const val SPORT: String = "SPORT"
        const val LEAGUE: String = "LEAGUE"
        const val ID_LEAGUE: String = "ID_LEAGUE"
        const val DIVISION: String = "DIVISION"
        const val MANAGER: String = "MANAGER"
        const val STADIUM: String = "STADIUM"
        const val KEYWORDS: String = "KEYWORDS"
        const val STADIUM_THUMB: String = "STADIUM_THUMB"
        const val STADIUM_DESCRIPTION: String = "STADIUM_DESCRIPTION"
        const val STADIUM_LOCATION: String = "STADIUM_LOCATION"
        const val STADIUM_CAPACITY: String = "STADIUM_CAPACITY"
        const val WEBSITE: String = "WEBSITE"
        const val FACEBOOK: String = "FACEBOOK"
        const val TWITTER: String = "TWITTER"
        const val INSTAGRAM: String = "INSTAGRAM"
        const val DESCRIPTION_EN: String = "DESCRIPTION_EN"
        const val GENDER: String = "GENDER"
        const val COUNTRY: String = "COUNTRY"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_JERSEY: String = "TEAM_JERSEY"
        const val TEAM_LOGO: String = "TEAM_LOGO"
        const val TEAM_BANNER: String = "TEAM_BANNER"
        const val YOUTUBE: String = "YOUTUBE"
    }
}
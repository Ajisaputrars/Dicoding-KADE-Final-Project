package com.example.ajisaputrars.submission4.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
//        @SerializedName("idEvent")
//        val idEvent: String? = null,
//
//        @SerializedName("idSoccerXML")
//        val idSoccerXML: String? = null,
//
//        @SerializedName("strEvent")
//        val strEvent: String? = null,
//
//        @SerializedName("strFilename")
//        val strFilename: String? = null,
//
//        @SerializedName("strSport")
//        val strSport: String? = null,
//
//        @SerializedName("idLeague")
//        val idLeague: String? = null,
//
//        @SerializedName("strLeague")
//        val strLeague: String? = null,
//
//        @SerializedName("strSeason")
//        val strSeason: String? = null,
//
//        @SerializedName("strDescriptionEN")
//        val strDescriptionEN: String? = null,
//
//        @SerializedName("strHomeTeam")
//        val strHomeTeam: String? = null,
//
//        @SerializedName("strAwayTeam")
//        val strAwayTeam: String? = null,
//
//        @SerializedName("intHomeScore")
//        val intHomeScore: String? = null,
//
//        @SerializedName("intRound")
//        val intRound: String? = null,
//
//        @SerializedName("intAwayScore")
//        val intAwayScore: String? = null,
//
//        @SerializedName("intSpectators")
//        val intSpectators: String? = null,
//
//        @SerializedName("strHomeGoalDetails")
//        val strHomeGoalDetails: String? = null,
//
//        @SerializedName("strHomeRedCards")
//        val strHomeRedCards: String? = null,
//
//        @SerializedName("strHomeYellowCards")
//        val strHomeYellowCards: String? = null,
//
//        @SerializedName("strHomeLineupGoalkeeper")
//        val strHomeLineupGoalkeeper: String? = null,
//
//        @SerializedName("strHomeLineupDefense")
//        val strHomeLineupDefense: String? = null,
//
//        @SerializedName("strHomeLineupMidfield")
//        val strHomeLineupMidfield: String? = null,
//
//        @SerializedName("strHomeLineupForward")
//        val strHomeLineupForward: String?? = null,
//
//        @SerializedName("strHomeLineupSubstitutes")
//        val strHomeLineupSubstitutes: String? = null,
//
//        @SerializedName("strHomeFormation")
//        val strHomeFormation: String? = null,
//
//        @SerializedName("strAwayRedCards")
//        val strAwayRedCards: String? = null,
//
//        @SerializedName("strAwayYellowCards")
//        val strAwayYellowCards: String? = null,
//
//        @SerializedName("strAwayGoalDetails")
//        val strAwayGoalDetails: String? = null,
//
//        @SerializedName("strAwayLineupGoalkeeper")
//        val strAwayLineupGoalkeeper: String? = null,
//
//        @SerializedName("strAwayLineupDefense")
//        val strAwayLineupDefense: String? = null,
//
//        @SerializedName("strAwayLineupMidfield")
//        val strAwayLineupMidfield: String? = null,
//
//        @SerializedName("strAwayLineupForward")
//        val strAwayLineupForward: String? = null,
//
//        @SerializedName("strAwayLineupSubstitutes")
//        val strAwayLineupSubstitutes: String? = null,
//
//        @SerializedName("strAwayFormation")
//        val strAwayFormation: String? = null,
//
//        @SerializedName("intHomeShots")
//        val intHomeShots: String? = null,
//
//        @SerializedName("intAwayShots")
//        val intAwayShots: String? = null,
//
//        @SerializedName("dateEvent")
//        val dateEvent: String? = null,
//
//        @SerializedName("strDate")
//        val strDate: String? = null,
//
//        @SerializedName("strTime")
//        val strTime: String? = null,
//
//        @SerializedName("strTVStation")
//        val strTVStation: String? = null,
//
//        @SerializedName("idHomeTeam")
//        val idHomeTeam: String? = null,
//
//        @SerializedName("idAwayTeam")
//        val idAwayTeam: String? = null,
//
//        @SerializedName("strResult")
//        val strResult: String? = null,
//
//        @SerializedName("strCircuit")
//        val strCircuit: String? = null,
//
//        @SerializedName("strCountry")
//        val strCountry: String? = null,
//
//        @SerializedName("strCity")
//        val strCity: String? = null,
//
//        @SerializedName("strPoster")
//        val strPoster: String? = null,
//
//        @SerializedName("strFanart")
//        val strFanart: String? = null,
//
//        @SerializedName("strThumb")
//        val strThumb: String? = null,
//
//        @SerializedName("strBanner")
//        val strBanner: String? = null,
//
//        @SerializedName("strMap")
//        val strMap: String? = null,
//
//        @SerializedName("strLocked")
//        val strLocked: String? = null

        val id: Long?, // This id is for database table
        @SerializedName("idEvent") val idEvent: String,
        @SerializedName("strSport") val sport: String,
        @SerializedName("idLeague") val idLeague: String,
        @SerializedName("strLeague") val league: String,
        @SerializedName("strSeason") val season: String,
        @SerializedName("strHomeTeam") val homeTeam: String,
        @SerializedName("strAwayTeam") val awayTeam: String,
        @SerializedName("intHomeScore") val homeScore: String?,
        @SerializedName("intAwayScore") val awayScore: String?,
        @SerializedName("strHomeGoalDetails") val homeGoalDetails: String?,
        @SerializedName("strHomeRedCards") val homeRedCards: String?,
        @SerializedName("strHomeYellowCards") val homeYellowCards: String?,
        @SerializedName("strHomeLineupGoalkeeper") val homeLineupGoalkeeper: String?,
        @SerializedName("strHomeLineupDefense") val homeLineupDefense: String?,
        @SerializedName("strHomeLineupMidfield")val homeLineupMidfield: String?,
        @SerializedName("strHomeLineupForward") val homeLineupForward: String?,
        @SerializedName("strHomeLineupSubstitutes") val homeLineupSubstitutes: String?,
        @SerializedName("strHomeFormation") val homeFormation: String?,
        @SerializedName("strAwayRedCards") val awayRedCards: String?,
        @SerializedName("strAwayYellowCards") val awayYellowCards: String?,
        @SerializedName("strAwayGoalDetails") val awayGoalDetails: String?,
        @SerializedName("strAwayLineupGoalkeeper") val awayLineupGoalkeeper: String?,
        @SerializedName("strAwayLineupDefense") val awayLineupDefense: String?,
        @SerializedName("strAwayLineupMidfield") val awayLineupMidfield: String?,
        @SerializedName("strAwayLineupForward") val awayLineupForward: String?,
        @SerializedName("strAwayLineupSubstitutes") val awayLineupSubstitutes: String?,
        @SerializedName("strAwayFormation") val awayFormation: String?,
        @SerializedName("intHomeShots") val homeShots: String?,
        @SerializedName("intAwayShots") val awayShots: String?,
        @SerializedName("dateEvent") val dateEvent: String,
        @SerializedName("strDate") val date: String?,
        @SerializedName("strTime") val time: String?,
        @SerializedName("idHomeTeam") val idHomeTeam: String,
        @SerializedName("idAwayTeam") val idAwayTeam: String
) : Parcelable {
    companion object {
        const val TABLE_EVENT: String = "TABLE_EVENT"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val SPORT: String = "SPORT"
        const val ID_LEAGUE: String = "ID_LEAGUE"
        const val LEAGUE: String = "LEAGUE"
        const val SEASON: String = "SEASON"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_GOAL_DETAILS: String = "HOME_GOAL_DETAILS"
        const val HOME_RED_CARDS: String = "HOME_RED_CARDS"
        const val HOME_YELLOW_CARDS: String = "HOME_YELLOW_CARDS"
        const val HOME_LINEUP_GOALKEEPER: String = "HOME_LINEUP_GOALKEEPER"
        const val HOME_LINEUP_DEFENSE: String = "HOME_LINEUP_DEFENSE"
        const val HOME_LINEUP_MIDFIELD: String = "HOME_LINEUP_MIDFIELD"
        const val HOME_LINEUP_FORWARD: String = "HOME_LINEUP_FORWARD"
        const val HOME_LINEUP_SUBSTITUTES: String = "HOME_LINEUP_SUBSTITUTES"
        const val HOME_FORMATION: String = "HOME_FORMATION"
        const val AWAY_RED_CARDS: String = "AWAY_RED_CARDS"
        const val AWAY_YELLOW_CARDS: String = "AWAY_YELLOW_CARDS"
        const val AWAY_GOAL_DETAILS: String = "AWAY_GOAL_DETAILS"
        const val AWAY_LINEUP_GOALKEEPER: String = "AWAY_LINEUP_GOALKEEPER"
        const val AWAY_LINEUP_DEFENSE: String = "AWAY_LINEUP_DEFENSE"
        const val AWAY_LINEUP_MIDFIELD: String = "AWAY_LINEUP_MIDFIELD"
        const val AWAY_LINEUP_FORWARD: String = "AWAY_LINEUP_FORWARD"
        const val AWAY_LINEUP_SUBSTITUTES: String = "AWAY_LINEUP_SUBSTITUTES"
        const val AWAY_FORMATION: String = "AWAY_FORMATION"
        const val HOME_SHOTS: String = "HOME_SHOTS"
        const val AWAY_SHOTS: String = "AWAY_SHOTS"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val DATE: String = "DATE"
        const val TIME: String = "TIME"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
    }
}
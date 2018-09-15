package com.example.ajisaputrars.submission4.api

import android.net.Uri
import android.util.Log
import com.example.ajisaputrars.finalprojectdicoding.BuildConfig

object TheSportDbApi {

    fun getEvent(league: String?): String {
        val b = BuildConfig.BASE_URL + BuildConfig.TSDB_API_KEY + "/" + league
        return b
    }

    fun getTeams(league: String?): String {
        val ss = BuildConfig.BASE_URL + "1/search_all_teams.php?l=" + league?.replace(" ","%20")
        return ss
    }

    fun getTeamPlayer(teamId: String?): String{
        val ss = BuildConfig.BASE_URL + "1/lookup_all_players.php?id=" + teamId
        return ss
    }

    fun getSearchTeams(name: String?): String {
        val ss = BuildConfig.BASE_URL + "1/searchteams.php?t=" + name
        return ss
    }

    fun getSearchMatches(name: String?): String {
        val ss = BuildConfig.BASE_URL + "1/searchevents.php?e=" + name
        return ss
    }
}
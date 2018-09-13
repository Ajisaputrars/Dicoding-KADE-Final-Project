package com.example.ajisaputrars.submission4.api

import android.net.Uri
import android.util.Log
import com.example.ajisaputrars.finalprojectdicoding.BuildConfig

object TheSportDbApi {

    fun getEvent(league: String?): String {
        val b = BuildConfig.BASE_URL +
                BuildConfig.TSDB_API_KEY +
                "/" +
                league
        Log.d("getEvent", "Linknya adalah = " + b)
        return b
    }

    fun getTeams(league: String?): String {
        val a =  Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("1")
                .appendPath("search_all_teams.php")
                .appendQueryParameter("l", league)
                .build()
                .toString()
        return a
    }

    fun getTeamPlayer(teamId: String?): String{
        val a = Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("1")
                .appendPath("lookup_all_players.php")
                .appendQueryParameter("id", teamId)
                .build()
                .toString()
        return a
    }
}
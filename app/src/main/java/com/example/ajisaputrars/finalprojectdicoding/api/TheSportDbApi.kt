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
        return b
    }

    fun getTeams(league: String?): String {
//        val a =  Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("1")
//                .appendPath("search_all_teams.php")
//                .appendQueryParameter("l", league)
//                .build()
//                .toString()
        val ss = BuildConfig.BASE_URL + "1/search_all_teams.php?l=" + league?.replace(" ","%20")
//        val text = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
//        Log.d("getTeams", "link adalah " + a)
//        Log.d("getTeams SS", "link adalah " + ss)
        return ss
    }

    fun getTeamPlayer(teamId: String?): String{
//        val a = Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("1")
//                .appendPath("lookup_all_players.php")
//                .appendQueryParameter("id", teamId)
//                .build()
//                .toString()
//        Log.d("getTeamPlayer", "link adalah " + a)
//        val text = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_players.php?id=133604"
        val ss = BuildConfig.BASE_URL + "1/lookup_all_players.php?id=" + teamId
//        Log.d("getTeamPlayerSS", "SS adalah " + ss)
        return ss
    }

    fun getSearchTeams(name: String?): String {
//        val a =  Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("1")
//                .appendPath("searchteams.php")
//                .appendQueryParameter("t", name)
//                .build()
//                .toString()
//        Log.d("getSearchTeams", "link adalah " + a)
//        val text = "https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=barc"
        val ss = BuildConfig.BASE_URL + "1/searchteams.php?t=" + name
        return ss
    }

    fun getSearchMatches(name: String?): String {
//        val a =  Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("1")
//                .appendPath("searchevents.php")
//                .appendQueryParameter("e", name)
//                .build()
//                .toString()
//        Log.d("getSearchMatches", "link adalah " + a)
//        val text = "https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=man"
        val ss = BuildConfig.BASE_URL + "1/searchevents.php?e=" + name
        return ss
    }
}
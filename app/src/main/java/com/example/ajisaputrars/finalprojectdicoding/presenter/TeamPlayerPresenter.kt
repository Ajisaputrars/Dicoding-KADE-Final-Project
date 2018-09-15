package com.example.ajisaputrars.finalprojectdicoding.presenter

import com.example.ajisaputrars.finalprojectdicoding.R.array.league
import com.example.ajisaputrars.finalprojectdicoding.interfaces.PlayerResponse
import com.example.ajisaputrars.finalprojectdicoding.interfaces.TeamPlayerView
import com.example.ajisaputrars.finalprojectdicoding.interfaces.TeamView
import com.example.ajisaputrars.submission4.api.ApiRepository
import com.example.ajisaputrars.submission4.api.TheSportDbApi
import com.example.ajisaputrars.submission4.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPlayerPresenter(private val view: TeamPlayerView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson) {

    fun getTeamPlayerList(teamID: String?) {
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getTeamPlayer(teamID)),
                    PlayerResponse::class.java
            )

            uiThread {
                view.showPlayerList(data.player)
            }
        }
    }
}
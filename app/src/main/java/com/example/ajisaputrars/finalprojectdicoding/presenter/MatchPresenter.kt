package com.example.ajisaputrars.finalprojectdicoding.presenter

import com.example.ajisaputrars.finalprojectdicoding.interfaces.MatchView
import com.example.ajisaputrars.finalprojectdicoding.util.CoroutineContextProvider
import com.example.ajisaputrars.submission4.api.ApiRepository
import com.example.ajisaputrars.submission4.api.TheSportDbApi
import com.example.ajisaputrars.submission4.model.EventResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenter(private val view: MatchView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()) : AnkoLogger {

    fun getTeamList(league: String?) {
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDbApi.getEvent(league)),
                        EventResponse::class.java
                )
            }
            view.showTeamList(data.await().events)
            view.hideLoading()
        }
    }
}
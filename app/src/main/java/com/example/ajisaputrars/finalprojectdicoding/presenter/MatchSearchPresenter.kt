package com.example.ajisaputrars.finalprojectdicoding.presenter

import android.util.Log
import com.example.ajisaputrars.finalprojectdicoding.R.array.league
import com.example.ajisaputrars.finalprojectdicoding.interfaces.MatchSearchView
import com.example.ajisaputrars.finalprojectdicoding.model.EventSearchResponse
import com.example.ajisaputrars.finalprojectdicoding.util.CoroutineContextProvider
import com.example.ajisaputrars.submission4.api.ApiRepository
import com.example.ajisaputrars.submission4.api.TheSportDbApi
import com.example.ajisaputrars.submission4.model.EventResponse
import com.example.ajisaputrars.submission4.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchSearchPresenter(private val view: MatchSearchView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,private val context: CoroutineContextProvider = CoroutineContextProvider()) : AnkoLogger {

    fun getMatchSearch(name: String?) {
        if (name != null) {
//            if (name.length > 2) {
//                doAsync {
//                    val data = gson.fromJson(apiRepository
//                            .doRequest(TheSportDbApi.getSearchMatches(name)),
//                            EventResponse::class.java
//                    )
//
//                    uiThread {
//                        view.showMatchSearchList(data.events)
//                    }
//                }
//            }

            async(context.main){
                val data = bg {
                    gson.fromJson(apiRepository
                            .doRequest(TheSportDbApi.getSearchMatches(name)),
                            EventSearchResponse::class.java
                    )
                }
                view.showMatchSearchList(data.await().event)
            }
        }
    }
}
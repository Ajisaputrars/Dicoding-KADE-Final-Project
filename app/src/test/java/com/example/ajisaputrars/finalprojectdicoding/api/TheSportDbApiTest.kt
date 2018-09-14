package com.example.ajisaputrars.finalprojectdicoding.api

import android.net.Uri
import com.example.ajisaputrars.finalprojectdicoding.util.toSimpleString
import com.example.ajisaputrars.submission4.api.ApiRepository
import com.example.ajisaputrars.submission4.api.TheSportDbApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
//import org.powermock.core.classloader.annotations.PrepareForTest
//import org.powermock.modules.junit4.PowerMockRunner

class TheSportDbApiTest {

    private lateinit var theSportDbApi: TheSportDbApi

    @Before
    fun setup() {
        theSportDbApi = TheSportDbApi
    }

    @Test
    fun getEvent() {
        val resultLink = "https://www.thesportsdb.com/api/v1/json/4012900/eventspastleague.php?id=4328"
        val stringInput = "eventspastleague.php?id=4328"
        assertEquals(resultLink, theSportDbApi.getEvent(stringInput))
    }

    @Test
    fun getTeams() {
        val resultLink = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        val stringInput = "English Premier League"
        assertEquals(resultLink, theSportDbApi.getTeams(stringInput))
    }

    @Test
    fun getTeamPlayer() {
        val resultLink = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_players.php?id=133604"
        val stringInput = "133604"
        assertEquals(resultLink, theSportDbApi.getTeamPlayer(stringInput))
    }

    @Test
    fun getSearchTeams() {
        val resultLink = "https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=barcelona"
        val stringInput = "barcelona"
        assertEquals(resultLink, theSportDbApi.getSearchTeams(stringInput))
    }

    @Test
    fun getSearchMatches() {
        val resultLink = "https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=manchester"
        val stringInput = "manchester"
        assertEquals(resultLink, theSportDbApi.getSearchMatches(stringInput))
    }
}
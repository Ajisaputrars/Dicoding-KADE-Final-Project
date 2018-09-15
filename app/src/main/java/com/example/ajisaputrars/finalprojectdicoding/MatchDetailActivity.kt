package com.example.ajisaputrars.finalprojectdicoding

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.ajisaputrars.finalprojectdicoding.db.database
import com.example.ajisaputrars.finalprojectdicoding.util.dateTimeToFormat
import com.example.ajisaputrars.submission4.model.Event
import com.example.ajisaputrars.submission4.model.TeamResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MatchDetailActivity: AppCompatActivity() {
    lateinit var detailTeamObject: Event
    private var homeBadge = ""
    private var awayBadge = ""

    private lateinit var menu: Menu
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        detailTeamObject = getIntent().extras
                .getParcelable("matchObject")
        favoriteState()
        setUI()
    }

    private fun favoriteState() {
        database.use {
            val result = select(Event.TABLE_EVENT)
                    .whereArgs("(${Event.ID_EVENT} = {id})",
                            "id" to detailTeamObject.idEvent)
            val favorite = result.parseList(classParser<Event>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    fun setUI(){
        getBadge(detailTeamObject.idHomeTeam!!, detailTeamObject.idAwayTeam!!)
        val timestampDateEvent = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(detailTeamObject.dateEvent)
        val dateSchedule = SimpleDateFormat("EEE, dd MMM yyyy", Locale.US).format(timestampDateEvent)
        text_view_date_schedule.text = dateSchedule
        text_view_home_score.text = detailTeamObject.homeScore
        Log.d("Score ", "Score adalah " + detailTeamObject.homeScore)
        text_view_away_score.text = detailTeamObject.awayScore
        text_view_home_club_name.text = detailTeamObject.homeTeam
        text_view_away_club_name.text = detailTeamObject.awayTeam
        text_view_home_goal.text = detailTeamObject.homeGoalDetails
        text_view_away_goal.text = detailTeamObject.awayGoalDetails
        text_view_home_goalkeeper.text = detailTeamObject.homeLineupGoalkeeper
        text_view_away_goalkeeper.text = detailTeamObject.awayLineupGoalkeeper
        text_view_home_defense.text = detailTeamObject.homeLineupDefense
        text_view_away_defense.text = detailTeamObject.awayLineupDefense
        text_view_home_midfield.text = detailTeamObject.homeLineupMidfield
        text_view_away_midfield.text = detailTeamObject.awayLineupMidfield
        text_view_home_forward.text = detailTeamObject.homeLineupForward
        text_view_away_forward.text = detailTeamObject.awayLineupForward
        text_view_home_substitute.text = detailTeamObject.homeLineupSubstitutes
        text_view_away_substitute.text = detailTeamObject.awayLineupSubstitutes
    }

    fun getBadge(homeID: String, awayID: String) {
        val gson = Gson()

        doAsync {
            val homeTeam = gson.fromJson(URL(setLinkForTeam(homeID)).readText(), TeamResponse::class.java)
            val awayTeam = gson.fromJson(URL(setLinkForTeam(awayID)).readText(), TeamResponse::class.java)

            homeBadge = (homeTeam.teams[0].teamBadge).toString()
            awayBadge = (awayTeam.teams[0].teamBadge).toString()

            uiThread {
                Picasso.get().load(homeBadge).into(image_view_home)
                Picasso.get().load(awayBadge).into(image_view_away)
            }
        }
    }

    fun setLinkForTeam(id: String): String {
        var text2 = BuildConfig.BASE_URL + BuildConfig.TSDB_API_KEY + "/lookupteam.php?id=" + id
        return text2
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_add_favorite_menu_detail_match -> {
                if (isFavorite) {
                    removeFromFavorite()
                    toast("Event deleted from Favorite")
                } else {
                    addToFavorite()
                    toast("Event saved to Favorite")
                }

                isFavorite = !isFavorite
                setFavorite()

                return true
            }

            R.id.menu_item_add_calendar_menu_detail_match -> {
                createEventOnCalendar(detailTeamObject)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun createEventOnCalendar(event: Event) {
        if (detailTeamObject.homeScore == null && detailTeamObject.awayScore == null) {
            val intent = Intent(Intent.ACTION_INSERT)
            intent.type = "vnd.android.cursor.item/event"

            val dateTime = event.dateEvent + " " + event.time
            Log.d("dateTime", "DateTime adalah " + dateTime)
            val startTime = dateTime.dateTimeToFormat()
            Log.d("startTime", "startTime adalah " + startTime)
            val endTime = startTime + TimeUnit.MINUTES.toMillis(90)

            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime)
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
            intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)

            intent.putExtra(CalendarContract.Events.TITLE,
                    "${event.homeTeam} vs ${event.awayTeam}")

            intent.putExtra(CalendarContract.Events.DESCRIPTION,
                    "Reminder ${event.homeTeam} and ${event.awayTeam} from My Football App")

            startActivity(intent)
        } else {
            toast("Useless added to calendar. This match had ended")
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Event.TABLE_EVENT,
                        Event.ID_EVENT to detailTeamObject.idEvent,
                        Event.SPORT to detailTeamObject.sport,
                        Event.ID_LEAGUE to detailTeamObject.idLeague,
                        Event.LEAGUE to detailTeamObject.league,
                        Event.SEASON to detailTeamObject.season,
                        Event.HOME_TEAM to detailTeamObject.homeTeam,
                        Event.AWAY_TEAM to detailTeamObject.awayTeam,
                        Event.HOME_SCORE to detailTeamObject.homeScore,
                        Event.AWAY_SCORE to detailTeamObject.awayScore,
                        Event.HOME_GOAL_DETAILS to detailTeamObject.homeGoalDetails,
                        Event.HOME_RED_CARDS to detailTeamObject.homeRedCards,
                        Event.HOME_YELLOW_CARDS to detailTeamObject.homeYellowCards,
                        Event.HOME_LINEUP_GOALKEEPER to detailTeamObject.homeLineupGoalkeeper,
                        Event.HOME_LINEUP_DEFENSE to detailTeamObject.homeLineupDefense,
                        Event.HOME_LINEUP_MIDFIELD to detailTeamObject.homeLineupMidfield,
                        Event.HOME_LINEUP_FORWARD to detailTeamObject.homeLineupForward,
                        Event.HOME_LINEUP_SUBSTITUTES to detailTeamObject.homeLineupSubstitutes,
                        Event.HOME_FORMATION to detailTeamObject.homeFormation,
                        Event.AWAY_RED_CARDS to detailTeamObject.awayRedCards,
                        Event.AWAY_YELLOW_CARDS to detailTeamObject.awayYellowCards,
                        Event.AWAY_GOAL_DETAILS to detailTeamObject.awayGoalDetails,
                        Event.AWAY_LINEUP_GOALKEEPER to detailTeamObject.awayLineupGoalkeeper,
                        Event.AWAY_LINEUP_DEFENSE to detailTeamObject.awayLineupDefense,
                        Event.AWAY_LINEUP_MIDFIELD to detailTeamObject.awayLineupMidfield,
                        Event.AWAY_LINEUP_FORWARD to detailTeamObject.awayLineupForward,
                        Event.AWAY_LINEUP_SUBSTITUTES to detailTeamObject.awayLineupSubstitutes,
                        Event.AWAY_FORMATION to detailTeamObject.awayFormation,
                        Event.HOME_SHOTS to detailTeamObject.homeShots,
                        Event.AWAY_SHOTS to detailTeamObject.awayShots,
                        Event.DATE_EVENT to detailTeamObject.dateEvent,
                        Event.DATE to detailTeamObject.date,
                        Event.TIME to detailTeamObject.time,
                        Event.ID_HOME_TEAM to detailTeamObject.idHomeTeam,
                        Event.ID_AWAY_TEAM to detailTeamObject.idAwayTeam)
            }
        } catch (e: SQLiteConstraintException) {
            toast("Error add to Favorite > " + e.localizedMessage)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Event.TABLE_EVENT, "(${Event.ID_EVENT} = {id})",
                        "id" to detailTeamObject.idEvent)
            }
        } catch (e: SQLiteConstraintException) {
            toast("Error detele Favorite > " + e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite) menu.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else menu.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

}
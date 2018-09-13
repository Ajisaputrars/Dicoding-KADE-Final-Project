package com.example.ajisaputrars.finalprojectdicoding.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.ajisaputrars.submission4.model.Event
import com.example.ajisaputrars.submission4.model.Team
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context)
    : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Event.TABLE_EVENT, true,
                Event.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Event.ID_EVENT to TEXT + UNIQUE,
                Event.SPORT to TEXT,
                Event.ID_LEAGUE to TEXT,
                Event.LEAGUE to TEXT,
                Event.SEASON to TEXT,
                Event.HOME_TEAM to TEXT,
                Event.AWAY_TEAM to TEXT,
                Event.HOME_SCORE to TEXT,
                Event.AWAY_SCORE to TEXT,
                Event.HOME_GOAL_DETAILS to TEXT,
                Event.HOME_RED_CARDS to TEXT,
                Event.HOME_YELLOW_CARDS to TEXT,
                Event.HOME_LINEUP_GOALKEEPER to TEXT,
                Event.HOME_LINEUP_DEFENSE to TEXT,
                Event.HOME_LINEUP_MIDFIELD to TEXT,
                Event.HOME_LINEUP_FORWARD to TEXT,
                Event.HOME_LINEUP_SUBSTITUTES to TEXT,
                Event.HOME_FORMATION to TEXT,
                Event.AWAY_RED_CARDS to TEXT,
                Event.AWAY_YELLOW_CARDS to TEXT,
                Event.AWAY_GOAL_DETAILS to TEXT,
                Event.AWAY_LINEUP_GOALKEEPER to TEXT,
                Event.AWAY_LINEUP_DEFENSE to TEXT,
                Event.AWAY_LINEUP_MIDFIELD to TEXT,
                Event.AWAY_LINEUP_FORWARD to TEXT,
                Event.AWAY_LINEUP_SUBSTITUTES to TEXT,
                Event.AWAY_FORMATION to TEXT,
                Event.HOME_SHOTS to TEXT,
                Event.AWAY_SHOTS to TEXT,
                Event.DATE_EVENT to TEXT,
                Event.DATE to TEXT,
                Event.TIME to TEXT,
                Event.ID_HOME_TEAM to TEXT,
                Event.ID_AWAY_TEAM to TEXT)

        db.createTable(Team.TABLE_TEAM, true,
                Team.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Team.ID_TEAM to TEXT + UNIQUE,
                Team.TEAM to TEXT,
                Team.TEAM_SHORT to TEXT,
                Team.ALTERNATE to TEXT,
                Team.FORMED_YEAR to TEXT,
                Team.SPORT to TEXT,
                Team.LEAGUE to TEXT,
                Team.ID_LEAGUE to TEXT,
                Team.DIVISION to TEXT,
                Team.MANAGER to TEXT,
                Team.STADIUM to TEXT,
                Team.KEYWORDS to TEXT,
                Team.STADIUM_THUMB to TEXT,
                Team.STADIUM_DESCRIPTION to TEXT,
                Team.STADIUM_LOCATION to TEXT,
                Team.STADIUM_CAPACITY to TEXT,
                Team.WEBSITE to TEXT,
                Team.FACEBOOK to TEXT,
                Team.TWITTER to TEXT,
                Team.INSTAGRAM to TEXT,
                Team.DESCRIPTION_EN to TEXT,
                Team.GENDER to TEXT,
                Team.COUNTRY to TEXT,
                Team.TEAM_BADGE to TEXT,
                Team.TEAM_JERSEY to TEXT,
                Team.TEAM_LOGO to TEXT,
                Team.TEAM_BANNER to TEXT,
                Team.YOUTUBE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Event.TABLE_EVENT, true)
        db.dropTable(Team.TABLE_TEAM, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
package com.example.ajisaputrars.finalprojectdicoding

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.ajisaputrars.finalprojectdicoding.db.database
import com.example.ajisaputrars.submission4.model.Event
import com.example.ajisaputrars.submission4.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class TeamDetailActivity : AppCompatActivity(){
    lateinit var teamDetailObject: Team
    private var mSectionsPagerAdapter: TeamDetailActivity.SectionsPagerAdapter? = null

    private lateinit var menu: Menu
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        teamDetailObject = getIntent().extras
                .getParcelable("teamObject")
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        viewpager_teamdetail.adapter = mSectionsPagerAdapter

        viewpager_teamdetail.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout_teamdetail))
        tablayout_teamdetail.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewpager_teamdetail))

        setUI()
    }

    fun setUI(){
        Picasso.get().load(teamDetailObject.teamBadge).into(imageview_teamdetail_badge)

        textview_teamdetail_name.text = teamDetailObject.team
        textview_teamdetail_formedyear.text = teamDetailObject.formedYear
        textview_teamdetail_stadium.text = teamDetailObject.stadium
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> {
                    return TeamOverviewFragment.newInstance(teamDetailObject.descriptionEN!!)
                }

                1 -> {
                    return TeamPlayerFragment.newInstance(teamDetailObject.idTeam)
                }

                else -> return null
            }
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 2
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        favoriteState()
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_add_favorite_menu_detail_match -> {
                if (isFavorite) {
                    removeFromFavorite()
                    toast("Event terhapus dari Favorit")
                } else {
                    addToFavorite()
                    toast("Event disimpan di Favorit")
                }

                isFavorite = !isFavorite
                setFavorite()

                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Team.TABLE_TEAM,
                        Team.ID_TEAM to teamDetailObject.idTeam,
                        Team.TEAM to teamDetailObject.team,
                        Team.TEAM_SHORT to teamDetailObject.teamShort,
                        Team.ALTERNATE to teamDetailObject.alternate,
                        Team.FORMED_YEAR to teamDetailObject.formedYear,
                        Team.SPORT to teamDetailObject.sport,
                        Team.LEAGUE to teamDetailObject.league,
                        Team.ID_LEAGUE to teamDetailObject.idLeague,
                        Team.DIVISION to teamDetailObject.division,
                        Team.MANAGER to teamDetailObject.manager,
                        Team.STADIUM to teamDetailObject.stadium,
                        Team.KEYWORDS to teamDetailObject.keywords,
                        Team.STADIUM_THUMB to teamDetailObject.stadiumThumb,
                        Team.STADIUM_DESCRIPTION to teamDetailObject.stadiumDescription,
                        Team.STADIUM_LOCATION to teamDetailObject.stadiumLocation,
                        Team.STADIUM_CAPACITY to teamDetailObject.stadiumCapacity,
                        Team.WEBSITE to teamDetailObject.website,
                        Team.FACEBOOK to teamDetailObject.facebook,
                        Team.TWITTER to teamDetailObject.twitter,
                        Team.INSTAGRAM to teamDetailObject.instagram,
                        Team.DESCRIPTION_EN to teamDetailObject.descriptionEN,
                        Team.GENDER to teamDetailObject.gender,
                        Team.COUNTRY to teamDetailObject.country,
                        Team.TEAM_BADGE to teamDetailObject.teamBadge,
                        Team.TEAM_JERSEY to teamDetailObject.teamJersey,
                        Team.TEAM_LOGO to teamDetailObject.teamLogo,
                        Team.TEAM_BANNER to teamDetailObject.teamBanner,
                        Team.YOUTUBE to teamDetailObject.youtube)
            }
            toast("Sukses Disimpan nih timnya")
        } catch (e: SQLiteConstraintException) {
            snackbar(container_teamdetail, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Team.TABLE_TEAM, "(${Team.ID_TEAM} = {id})",
                        "id" to teamDetailObject.idTeam)
            }
            toast("Berhasil dihapus nih timnya")
        } catch (e: SQLiteConstraintException) {
            snackbar(container_teamdetail, e.localizedMessage).show()
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(Team.TABLE_TEAM)
                    .whereArgs("(${Team.ID_TEAM} = {id})",
                            "id" to teamDetailObject.idTeam)
            val favorite = result.parseList(classParser<Team>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menu.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menu.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

}
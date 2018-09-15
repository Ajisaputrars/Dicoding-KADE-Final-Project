package com.example.ajisaputrars.finalprojectdicoding

import android.content.Context
import android.database.sqlite.SQLiteAbortException
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ajisaputrars.finalprojectdicoding.R.id.listEvent
import com.example.ajisaputrars.finalprojectdicoding.R.string.favorites
import com.example.ajisaputrars.finalprojectdicoding.adapter.MatchAdapter
import com.example.ajisaputrars.finalprojectdicoding.adapter.TeamAdapter
import com.example.ajisaputrars.finalprojectdicoding.db.database
import com.example.ajisaputrars.submission4.model.Event
import kotlinx.coroutines.experimental.selects.select
import org.jetbrains.anko.*
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*
import com.example.ajisaputrars.finalprojectdicoding.db.database
import com.example.ajisaputrars.submission4.model.Team

class FavTeamFragment : Fragment(), AnkoComponent<Context> {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var listEvent: RecyclerView

    private lateinit var adapter: TeamAdapter
    private var favorites: MutableList<Team> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = TeamAdapter(favorites) {
            startActivity<TeamDetailActivity>(
                    "teamObject" to it
            )
        }

        swipeRefreshLayout.onRefresh {
            swipeRefreshLayout.isRefreshing = false
        }

        listEvent.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        setDataFromDatabase()
    }

    private fun setDataFromDatabase() {
        try {
            context?.database?.use {
                val result = select(Team.TABLE_TEAM)
                val favorite: List<Team> = result.parseList(classParser())

                favorites.clear()
                favorites.addAll(favorite)
                adapter.notifyDataSetChanged()
            }
        } catch (e: SQLiteAbortException) {
            e.printStackTrace()
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefreshLayout = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                listEvent = recyclerView {
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }
}
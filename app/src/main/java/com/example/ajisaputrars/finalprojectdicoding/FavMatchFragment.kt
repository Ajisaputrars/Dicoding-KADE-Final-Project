package com.example.ajisaputrars.finalprojectdicoding

import android.content.Context
import android.database.sqlite.SQLiteAbortException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.EventLog
import android.widget.ProgressBar
import android.database.sqlite.SQLiteConstraintException
import com.example.ajisaputrars.finalprojectdicoding.adapter.MatchAdapter
import com.example.ajisaputrars.submission4.model.Event
import kotlinx.coroutines.experimental.selects.select
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import com.example.ajisaputrars.finalprojectdicoding.db.database
import android.database.sqlite.SQLiteDatabase
import com.example.ajisaputrars.submission4.model.Team
import org.jetbrains.anko.db.*
import org.jetbrains.anko.support.v4.*


class FavMatchFragment : Fragment(), AnkoComponent<Context> {
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: MatchAdapter

    private var favorites: MutableList<Event> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = MatchAdapter(favorites) {
            startActivity<MatchDetailActivity>(
                    "matchObject" to it
            )
        }

        swipeRefreshLayout.onRefresh {
            swipeRefreshLayout.isRefreshing = false
        }
        recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        setDataFromDatabase()
    }

    fun setDataFromDatabase() {
        try {
            context?.database?.use {
                val result = select(Event.TABLE_EVENT)
                val favorite: List<Event> = result.parseList(classParser())
                favorites.clear()
                favorites.addAll(favorite)
                adapter.notifyDataSetChanged()
            }
        } catch (e: SQLiteAbortException) {
            e.printStackTrace()
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            swipeRefreshLayout = swipeRefreshLayout {
                setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout() {
                    lparams(width = matchParent, height = wrapContent)

                    recyclerView = recyclerView {
                        id = R.id.list_fav
                        backgroundColor = android.R.color.darker_gray
                        topPadding = dip(16)
                        rightPadding = dip(16)
                        bottomPadding = dip(16)
                        leftPadding = dip(16)

                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                        addItemDecoration(DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL))
                    }
                }
            }
        }
    }
}

package com.example.ajisaputrars.finalprojectdicoding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.*
import com.example.ajisaputrars.finalprojectdicoding.adapter.MatchAdapter
import com.example.ajisaputrars.finalprojectdicoding.interfaces.MatchView
import com.example.ajisaputrars.finalprojectdicoding.presenter.MatchPresenter
import com.example.ajisaputrars.finalprojectdicoding.util.invisible
import com.example.ajisaputrars.finalprojectdicoding.util.visible
import com.example.ajisaputrars.submission4.api.ApiRepository
import com.example.ajisaputrars.submission4.model.Event
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*


class NextMatchFragment : Fragment(), AnkoComponent<Context>, MatchView {
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner

    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MatchPresenter
    private var eventMatchLeague: MutableList<Event> = mutableListOf()

    private var leaguePreviousStr = "eventspastleague.php?id=4328"
    private var leagueNextStr = "eventsnextleague.php?id=4328"
    private var isPreviousLeagueMatchSelected = true


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("onActivityCreated", "onActivityCreated dijalankan")

        adapter = MatchAdapter(eventMatchLeague) {
            toast("Tap berhasil")
            startActivity<MatchDetailActivity>(
                    "matchObject" to it
            )        }

        recyclerView.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)

        presenter.getTeamList(leagueNextStr)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                eventMatchLeague.clear()
                adapter.notifyDataSetChanged()

                setIdWithPosition(position)
                presenter.getTeamList(leagueNextStr)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefreshLayout.onRefresh {
            eventMatchLeague.clear()
            adapter.notifyDataSetChanged()
            presenter.getTeamList(leagueNextStr)
        }
    }

    fun setIdWithPosition(position: Int){
        if (position == 0){
            leagueNextStr = "eventsnextleague.php?id=4328"
        } else if (position == 1){
            leagueNextStr = "eventsnextleague.php?id=4329"
        } else if (position == 2) {
            leagueNextStr = "eventsnextleague.php?id=4331"
        } else if (position == 3){
            leagueNextStr = "eventsnextleague.php?id=4332"
        } else if (position == 4){
            leagueNextStr = "eventsnextleague.php?id=4334"
        } else if (position == 5){
            leagueNextStr = "eventsnextleague.php?id=4335"
        }
    }

    override fun showTeamList(data: List<Event>) {
        swipeRefreshLayout.isRefreshing = false
        eventMatchLeague.clear()
        eventMatchLeague.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        return inflater.inflate(R.layout.fragment_match_next, container,false)
        return createView(AnkoContext.create(ctx))

    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner {
                id = R.id.spinner
            }

            swipeRefreshLayout = swipeRefreshLayout {
                setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout(){
                    lparams (width = matchParent, height = wrapContent)

                    recyclerView = recyclerView {
                        id = R.id.list_team
                        backgroundColor = android.R.color.darker_gray
                        topPadding = dip(16)
                        rightPadding = dip(16)
                        bottomPadding = dip(16)
                        leftPadding = dip(16)

                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                        addItemDecoration(DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL))
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }
}
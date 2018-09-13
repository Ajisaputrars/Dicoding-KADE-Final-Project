package com.example.ajisaputrars.finalprojectdicoding

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.ajisaputrars.finalprojectdicoding.R.string.teams
import com.example.ajisaputrars.finalprojectdicoding.adapter.TeamAdapter
import com.example.ajisaputrars.finalprojectdicoding.interfaces.TeamSearchView
import com.example.ajisaputrars.finalprojectdicoding.presenter.TeamSearchPresenter
import com.example.ajisaputrars.finalprojectdicoding.util.gone
import com.example.ajisaputrars.finalprojectdicoding.util.visible
import com.example.ajisaputrars.submission4.api.ApiRepository
import com.example.ajisaputrars.submission4.model.Team
import com.google.gson.Gson
import kotlinx.android.synthetic.main.actvity_search.*
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class TeamSearchActivity : AppCompatActivity(), TeamSearchView, AnkoComponent<Context> {

    private lateinit var searchView: SearchView
    private val teamsSearch: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamSearchPresenter
    private lateinit var listTeamSearch: RecyclerView


    private lateinit var adapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.actvity_search)
        setContentView(createView(AnkoContext.create(ctx)))

//        setupToolbar()
//        setupRecyclerView()
        adapter = TeamAdapter(teamsSearch) {
            Log.d("ID Team", "ID untuk " + it.team + " adalah = " + it.idTeam)
            startActivity<TeamDetailActivity>(
                    "teamObject" to it
            )
        }
        listTeamSearch.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamSearchPresenter(this, request, gson)
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Search"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val menuSearch = menu?.findItem(R.id.action_search)

        searchView = menuSearch?.actionView as SearchView
        setupSearchView()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupSearchView() {
        searchView.queryHint = "Search"
        searchView.isIconified = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == null || newText == "") {
                    teamsSearch.clear()
                    adapter.notifyDataSetChanged()
                    Log.d("NewTextNull", "NewText adalah = " + newText)
                } else {
                    presenter.getTeamsSearch(newText)
                    Log.d("NewText", "NewText adalah = " + newText)
                }
                return true
            }
        })
    }

    override fun showTeamSearchList(data: List<Team>?) {
        if (data== null) {
            Log.d("Data Null", "Data null nich")
        } else {
            teamsSearch.clear()
            teamsSearch.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            listTeamSearch = recyclerView {
                id = R.id.listEvent
                lparams(width = matchParent, height = wrapContent)
                layoutManager = LinearLayoutManager(ctx)
            }
        }
    }
}
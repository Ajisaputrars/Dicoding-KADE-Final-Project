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
import com.example.ajisaputrars.finalprojectdicoding.adapter.MatchAdapter
import com.example.ajisaputrars.finalprojectdicoding.adapter.TeamAdapter
import com.example.ajisaputrars.finalprojectdicoding.interfaces.MatchSearchView
import com.example.ajisaputrars.finalprojectdicoding.interfaces.TeamSearchView
import com.example.ajisaputrars.finalprojectdicoding.presenter.MatchPresenter
import com.example.ajisaputrars.finalprojectdicoding.presenter.MatchSearchPresenter
import com.example.ajisaputrars.finalprojectdicoding.presenter.TeamSearchPresenter
import com.example.ajisaputrars.submission4.api.ApiRepository
import com.example.ajisaputrars.submission4.model.Event
import com.example.ajisaputrars.submission4.model.Team
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MatchSearchActivity : AppCompatActivity(), AnkoComponent<Context>, MatchSearchView {

    private lateinit var searchView: SearchView
    private val matchesSearch: MutableList<Event> = mutableListOf()
    private lateinit var presenter: MatchSearchPresenter
    private lateinit var listMatchSearch: RecyclerView
    private lateinit var adapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(createView(AnkoContext.create(ctx)))

        adapter = MatchAdapter(matchesSearch) {
            startActivity<MatchDetailActivity>(
                    "matchObject" to it
            )
        }
        listMatchSearch.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchSearchPresenter(this, request, gson)
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
                    matchesSearch.clear()
                    adapter.notifyDataSetChanged()
                    Log.d("NewTextNull", "NewText adalah = " + newText)
                } else {
                    presenter.getMatchSearch(newText)
                    Log.d("NewText", "NewText adalah = " + newText)
                }
                return true
            }
        })
    }

    override fun showMatchSearchList(data: List<Event>?) {
        if (data== null) {
            Log.d("Data Null", "Data null nich")
        } else {
            matchesSearch.clear()
            matchesSearch.addAll(data)
            adapter.notifyDataSetChanged()
            Log.d("Data Gak Null", "Data pertama adalah " + matchesSearch[0].homeTeam + " VS " + matchesSearch[0].awayTeam)
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            listMatchSearch = recyclerView {
                id = R.id.listEvent
                lparams(width = matchParent, height = wrapContent)
                layoutManager = LinearLayoutManager(ctx)
            }
        }
    }
}

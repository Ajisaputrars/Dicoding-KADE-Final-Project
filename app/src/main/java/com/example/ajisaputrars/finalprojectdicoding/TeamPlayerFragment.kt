package com.example.ajisaputrars.finalprojectdicoding

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.ajisaputrars.finalprojectdicoding.R.id.listEvent
import com.example.ajisaputrars.finalprojectdicoding.R.string.teams
import com.example.ajisaputrars.finalprojectdicoding.adapter.PlayerAdapter
import com.example.ajisaputrars.finalprojectdicoding.adapter.TeamAdapter
import com.example.ajisaputrars.finalprojectdicoding.interfaces.Player
import com.example.ajisaputrars.finalprojectdicoding.interfaces.TeamPlayerView
import com.example.ajisaputrars.finalprojectdicoding.presenter.TeamPlayerPresenter
import com.example.ajisaputrars.finalprojectdicoding.presenter.TeamPresenter
import com.example.ajisaputrars.finalprojectdicoding.util.nullToEmpty
import com.example.ajisaputrars.submission4.api.ApiRepository
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast

class TeamPlayerFragment : Fragment(), AnkoComponent<Context>, TeamPlayerView {

//    private lateinit var rootView: View
    private lateinit var presenter: TeamPlayerPresenter
    private lateinit var adapter: PlayerAdapter

    private val players: MutableList<Player> = mutableListOf()
    private lateinit var idTeam: String
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listPlayer: RecyclerView

    companion object {
        const val ARG_ID_TEAM = "ID_TEAM"

        fun newInstance(idTeam: String): TeamPlayerFragment {
            val fragment = TeamPlayerFragment()
            val args = Bundle()
            args.putString(ARG_ID_TEAM, idTeam)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        idTeam = arguments!!.getString(ARG_ID_TEAM)
        Log.d("ID TEAM", "IDnya adalah = " + idTeam)

        adapter = PlayerAdapter(players){
            startActivity<PlayerDetailActivity>(
                    "playerObject" to it
            )
        }

        listPlayer.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPlayerPresenter(this, request, gson)
        presenter.getTeamPlayerList(idTeam)
    }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return createView(AnkoContext.create(ctx))
    }

    override fun showPlayerList(data: List<Player>?) {
        if (data == null) {
            Log.d("Player Null", "Null nih playernya")
        } else {
            players.clear()
            players.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listPlayer = recyclerView {
                        id = R.id.listPlayer
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                }
            }
        }
    }
}

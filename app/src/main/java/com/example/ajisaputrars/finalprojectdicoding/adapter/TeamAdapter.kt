package com.example.ajisaputrars.finalprojectdicoding.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ajisaputrars.finalprojectdicoding.R.id.team_badge
import com.example.ajisaputrars.finalprojectdicoding.R.id.team_name
import com.example.ajisaputrars.submission4.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class TeamAdapter(private val teams: List<Team>, private val listener: (Team) -> Unit)
    : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.TeamViewHolder {
        return TeamAdapter.TeamViewHolder(TeamAdapter.TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamAdapter.TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.size

    class TeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = team_badge
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = team_name
                        textSize = 16f
                    }.lparams{
                        margin = dip(15)
                    }
                }
            }
        }
    }

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val teamBadge: ImageView = view.find(team_badge)
        private val teamName: TextView = view.find(team_name)

        fun bindItem(teams: Team, listener: (Team) -> Unit) {
            Picasso.get().load(teams.teamBadge).into(teamBadge)
            teamName.text = teams.team
            itemView.onClick { listener(teams) }
        }
    }
}


package com.example.ajisaputrars.finalprojectdicoding.adapter

import android.support.v7.widget.RecyclerView
import android.util.EventLog
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ajisaputrars.finalprojectdicoding.R
import com.example.ajisaputrars.finalprojectdicoding.R.id.*
import com.example.ajisaputrars.submission4.model.Event
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

class MatchAdapter(private val teams: List<Event>, val listener: (Event) -> Unit)
    : RecyclerView.Adapter<MatchAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(MatchAdapter.MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.size


    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamHome: TextView = view.find(team_home)
        private val scoreHome: TextView = view.find(score_home)
        private val teamAway: TextView = view.find(team_away)
        private val scoreAway: TextView = view.find(score_away)
        private val dateMatch: TextView = view.find(date_match)

        fun bindItem(teams: Event, listener: (Event) -> Unit) {
            val timestampDateEvent = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(teams.dateEvent)
            val dateSchedule = SimpleDateFormat("EEE, dd MMM yyyy", Locale.US).format(timestampDateEvent)
            teamHome.text = teams.homeTeam
            scoreHome.text = teams.homeScore
            teamAway.text = teams.awayTeam
            scoreAway.text = teams.awayScore
            dateMatch.text = dateSchedule

            itemView.setOnClickListener {
                listener(teams)
            }
        }
    }

    class MatchUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    topPadding = dip(16)
                    rightPadding = dip(16)
                    bottomPadding = dip(16)
                    leftPadding = dip(16)

                    textView {
                        id = date_match
                        textSize = 14f
                        gravity = Gravity.CENTER
//                        text = "20/08/1995"
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        bottomMargin = dip(10)
                        centerHorizontally()
                    }

                    textView {
                        textSize = 14f
                        text = "—"
                        id = text_vs
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        rightMargin = dip(10)
                        leftMargin = dip(10)
                        below(R.id.date_match)
                        centerHorizontally()
                    }

                    textView {
                        id = score_home
//                        text = "1"
                        textSize = 14f
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        leftMargin = dip(20)
                        below(R.id.date_match)
                        leftOf(R.id.text_vs)
                    }

                    textView {
                        id = score_away
                        textSize = 14f
//                        text = "0"
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        rightMargin = dip(20)
                        below(R.id.date_match)
                        rightOf(R.id.text_vs)
                    }


                    textView {
                        id = team_home
                        textSize = 14f
//                        text = "Man City"
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        below(R.id.date_match)
                        leftOf(R.id.score_home)
                    }

                    textView {
                        id = team_away
                        textSize = 14f
//                        text = "Man United"
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        below(R.id.date_match)
                        rightOf(R.id.score_away)
                    }
                }
            }
        }
    }
}


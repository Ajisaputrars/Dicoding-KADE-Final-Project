package com.example.ajisaputrars.finalprojectdicoding.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ajisaputrars.finalprojectdicoding.R
import com.example.ajisaputrars.finalprojectdicoding.R.string.teams
import com.example.ajisaputrars.finalprojectdicoding.interfaces.Player
import com.example.ajisaputrars.submission4.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class PlayerAdapter(private val players: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerAdapter.TeamViewHolder {
        return PlayerAdapter.TeamViewHolder(PlayerAdapter.TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: PlayerAdapter.TeamViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

    override fun getItemCount(): Int = players.size

    class TeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.player_badge
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }

                    linearLayout {
                        lparams(width = wrapContent, height = wrapContent)
                        orientation = LinearLayout.VERTICAL

                        textView {
                            id = R.id.player_name
                            textSize = 19f
//                            text = "Nama nich"
                            textColor = R.color.colorAccent
                        }.lparams{
                            leftMargin = dip(15)
                        }

                        textView {
                            id = R.id.player_position
                            textSize = 16f
                            textAlignment = right
//                            text = "Posisinya nich"
                        }.lparams{
                            topMargin = dip(5)
                            leftMargin = dip(15)
                        }
                    }
                }
            }
        }
    }

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val playerBadge: ImageView = view.find(R.id.player_badge)
        private val playerName: TextView = view.find(R.id.player_name)
        private val playerPosition: TextView = view.find(R.id.player_position)

        fun bindItem(player: Player, listener: (Player) -> Unit) {
            Picasso.get().load(player.cutout).into(playerBadge)
            playerName.text = player.player
            playerPosition.text = player.position
            itemView.onClick { listener(player) }
        }
    }
}


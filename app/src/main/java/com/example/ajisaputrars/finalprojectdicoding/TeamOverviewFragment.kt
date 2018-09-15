package com.example.ajisaputrars.finalprojectdicoding

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.nestedScrollView

class TeamOverviewFragment : Fragment(), AnkoComponent<Context> {

    private var teamDescription: String? = null

    private lateinit var teamDescriptionTextView: TextView

    companion object {

        const val TEAM_DESCRIPTION = "TEAM_DESCRIPTION"

        fun newInstance(teamDescription: String): TeamOverviewFragment {
            val fragment = TeamOverviewFragment()

            val args = Bundle()
            args.putString(TEAM_DESCRIPTION, teamDescription)

            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        teamDescription = arguments?.getString(TEAM_DESCRIPTION)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        nestedScrollView {
            lparams(width = matchParent, height = wrapContent)

            teamDescriptionTextView = textView {
                setLineSpacing(0f, 1.5f)
            }.lparams {
                margin = dip(14)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        teamDescriptionTextView.text = teamDescription
    }
}
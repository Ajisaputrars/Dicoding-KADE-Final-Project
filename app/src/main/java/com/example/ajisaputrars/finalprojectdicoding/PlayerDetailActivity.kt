package com.example.ajisaputrars.finalprojectdicoding

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ajisaputrars.finalprojectdicoding.interfaces.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class PlayerDetailActivity : AppCompatActivity(), AnkoComponent<Context> {

    private lateinit var player: Player
    private lateinit var playerDescriptionTextView: TextView
    private lateinit var playerPositionTextView: TextView
    private lateinit var playerHeightTextView: TextView
    private lateinit var playerWeightTextView: TextView
    private lateinit var playerImageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(createView(AnkoContext.create(ctx)))

        val intent = intent
        player = intent.getParcelableExtra("playerObject")

        setUI()
    }

    fun setUI(){
        Picasso.get().load(player.fanart).into(playerImageView)
        playerDescriptionTextView.text = player.descriptionEN
        playerWeightTextView.text = player.weight
        playerHeightTextView.text = player.height
        playerPositionTextView.text = player.position
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            playerImageView = imageView {
                scaleType = ImageView.ScaleType.CENTER_CROP
            }.lparams(width = matchParent, height = dip(120))

            view {
                backgroundColor = R.color.colorAccent
            }.lparams{
                height = dip(1)
                width = matchParent
                topMargin = dip(10)
            }

            relativeLayout {
                lparams (width = matchParent, height = wrapContent)

                linearLayout {
                    lparams (width = matchParent, height = wrapContent)
                    orientation = LinearLayout.VERTICAL
                    id = R.id.first_item_player_detail

                    textView {
                        text = "Weight (Kg)"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER

                    }.lparams {
                        topMargin = dip(5)
                    }

                    playerWeightTextView = textView {
                        text = "60 Kg"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }
                }.lparams {
                    leftOf(R.id.second_item_player_detail)
                }

                linearLayout {
                    lparams (width = matchParent, height = wrapContent)
                    orientation = LinearLayout.VERTICAL
                    id = R.id.second_item_player_detail

                    textView {
                        text = "Height (M)"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER

                    }.lparams {
                        topMargin = dip(5)
                    }

                    playerHeightTextView = textView {
                        text = "60 M"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }
                }.lparams {
                    //                    leftOf(R.id.second_item_player_detail)
                    centerHorizontally()
                    leftMargin = dip(30)
                    rightMargin = dip(30)
                }


                linearLayout {
                    lparams (width = matchParent, height = wrapContent)
                    orientation = LinearLayout.VERTICAL
                    id = R.id.third_item_player_detail

                    textView {
                        text = "Play Position"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams {
                        topMargin = dip(5)
                    }

                    playerPositionTextView = textView {
                        text = "Forward"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }
                }.lparams{
                    rightOf(R.id.second_item_player_detail)
                }
            }

            view {
                backgroundColor = R.color.colorAccent
            }.lparams {
                height = dip(1)
                width = matchParent
                bottomMargin = dip(10)
                topMargin = dip(10)
            }

            scrollView{
                playerDescriptionTextView = textView {
                    setLineSpacing(0f, 1.5f)
                }
            }
        }
    }
}

package com.example.ajisaputrars.finalprojectdicoding.interfaces

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
        @SerializedName("idPlayer") val idPlayer: String?,
        @SerializedName("idTeam") val idTeam: String?,
        @SerializedName("strNationality") val nationality: String?,
        @SerializedName("strPlayer") val player: String?,
        @SerializedName("strTeam") val team: String?,
        @SerializedName("strSport") val sport: String?,
        @SerializedName("dateBorn") val dateBorn: String?,
        @SerializedName("dateSigned") val dateSigned: String?,
        @SerializedName("strSigning") val signing: String?,
        @SerializedName("strBirthLocation") val birthLocation: String?,
        @SerializedName("strDescriptionEN") val descriptionEN: String?,
        @SerializedName("strGender") val gender: String?,
        @SerializedName("strPosition") val position: String?,
        @SerializedName("strFacebook") val facebook: String?,
        @SerializedName("strWebsite") val website: String?,
        @SerializedName("strTwitter") val twitter: String?,
        @SerializedName("strInstagram") val instagram: String?,
        @SerializedName("strYoutube") val youtube: String?,
        @SerializedName("strHeight") val height: String?,
        @SerializedName("strWeight") val weight: String?,
        @SerializedName("strThumb") val thumb: String?,
        @SerializedName("strCutout") val cutout: String?,
        @SerializedName("strBanner") val banner: String?,
        @SerializedName("strFanart1") val fanart: String?
) : Parcelable
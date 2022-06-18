package com.example.aroundegypttask.data.model.experience

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ExperienceRemote(
    @SerializedName("id") var id: String,
    @SerializedName("title") var title: String? = null,
    @SerializedName("cover_photo") var coverPhoto: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("views_no") var viewsNo: Int? = null,
    @SerializedName("likes_no") var likesNo: Int? = null,
    @SerializedName("recommended") var recommended: Int? = null,
    @SerializedName("has_video") var hasVideo: Int? = null,
//    @SerializedName("tags") var tags: ArrayList<Tags> = arrayListOf(),
//    @SerializedName("city") var city: City? = City(),
    @SerializedName("tour_html") var tourHtml: String? = null,
    @SerializedName("famous_figure") var famousFigure: String? = null,
//    @SerializedName("period") var period: Period? = Period(),
//    @SerializedName("era") var era: Era? = Era(),
    @SerializedName("founded") var founded: String? = null,
    @SerializedName("detailed_description") var detailedDescription: String? = null,
    @SerializedName("address") var address: String? = null,
//    @SerializedName("gmap_location") var gmapLocation: GmapLocation? = GmapLocation(),
//    @SerializedName("opening_hours") var openingHours: OpeningHours? = OpeningHours(),
    @SerializedName("starting_price") var startingPrice: Int? = null,
//    @SerializedName("ticket_prices") var ticketPrices: ArrayList<TicketPrices> = arrayListOf(),
//    @SerializedName("experience_tips") var experienceTips: ArrayList<String> = arrayListOf(),
    @SerializedName("is_liked") var isLiked: String? = null,
//    @SerializedName("reviews") var reviews: ArrayList<Reviews> = arrayListOf(),
    @SerializedName("rating") var rating: Int? = null,
    @SerializedName("reviews_no") var reviewsNo: Int? = null,
    @SerializedName("audio_url") var audioUrl: String? = null,
    @SerializedName("has_audio") var hasAudio: Boolean? = null

)
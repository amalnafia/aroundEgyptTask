package com.example.aroundegypttask.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Experience")
data class Experience(
    @PrimaryKey
    var id: String,
    var title: String? = null,
    var coverPhoto: String? = null,
    var description: String? = null,
    var viewsNo: Int? = null,
    var likesNo: Int? = null,
    var recommended: Int? = null,
    var tourHtml: String? = null,
    var famousFigure: String? = null,
    var founded: String? = null,
    var detailedDescription: String? = null,
    var address: String? = null,
    var startingPrice: Int? = null,
    var isLiked: Int,
    var rating: Int? = null,
    var reviewsNo: Int? = null,

    )
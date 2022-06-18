package com.example.aroundegypttask.data.model

import com.example.aroundegypttask.data.model.experience.ExperienceRemote
import com.example.aroundegypttask.domain.model.Experience

object ExperienceRemoteMapper {

    fun map(experienceRemote: ExperienceRemote): Experience {
        return Experience(
            id = experienceRemote.id,
            title = experienceRemote.title,
            coverPhoto = experienceRemote.coverPhoto,
            description = experienceRemote.description,
            viewsNo = experienceRemote.viewsNo,
            likesNo = experienceRemote.likesNo,
            recommended = experienceRemote.recommended,
            tourHtml = experienceRemote.tourHtml,
            famousFigure = experienceRemote.famousFigure,
            founded = experienceRemote.founded,
            detailedDescription = experienceRemote.detailedDescription,
            address = experienceRemote.address,
            startingPrice = experienceRemote.startingPrice,
            isLiked = mapIsLike(experienceRemote),
            rating = experienceRemote.rating,
            reviewsNo = experienceRemote.reviewsNo
        )
    }

    private fun mapIsLike(experienceRemote: ExperienceRemote): Int {
        return if (experienceRemote.isLiked == null)
            0
        else 1
    }

    fun map(experienceList: List<ExperienceRemote>): List<Experience> {
        return experienceList.map {
            map(it)
        }
    }
}

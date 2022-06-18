package com.example.aroundegypttask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aroundegypttask.domain.model.Experience
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface DataBaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(experience: List<Experience>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExperience(experience: Experience)

    @Query("SELECT * FROM Experience")
    fun selectExperienceList(): Single<List<Experience>>

    @Query("SELECT * FROM Experience where id LIKE :id")
    fun selectExperience(id: String): Single<Experience>

    @Query("SELECT * FROM Experience where recommended=1")
    fun selectRecommendedExperienceList(): Single<List<Experience>>


    @Query("UPDATE Experience SET likesNo=:likeNo ,isLiked=:isLiked WHERE id =:id")
    fun updateLikeNo(id: String, likeNo: String, isLiked: Int)

}
package com.sbjr.navigationdrawer
import androidx.room.*

@Dao
interface SavedMatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatch(match: SavedData)

    @Delete
    suspend fun deleteMatch(match: SavedData)

    @Query("SELECT * FROM saved_matches")
    suspend fun getAllMatches(): List<SavedData>

    @Query("SELECT EXISTS(SELECT 1 FROM saved_matches WHERE id = :matchId)")
    suspend fun isMatchSaved(matchId: String): Boolean
}

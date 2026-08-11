package dev.kolin.saltmine.db.game

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity
internal data class GameEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val players: Int,
    val format: String,
)

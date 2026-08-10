package dev.kolin.saltmine.db.entities

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity
public data class GameEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val players: Int,
    val format: String,
)

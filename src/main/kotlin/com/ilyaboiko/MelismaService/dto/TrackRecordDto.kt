package com.ilyaboiko.MelismaService.dto

import com.ilyaboiko.MelismaService.model.TrackEntity
import com.ilyaboiko.MelismaService.model.UserEntity
import org.hibernate.sql.Insert
import java.sql.Timestamp
import java.time.Instant

data class TrackRecordDto(
    val id: String,
    val trackName: String,
    val trackUrl: String,
    val smallImageUrl: String,
    val bigImageUrl: String,
    val albumName: String,
    val artistName: String,
    val durationInSeconds: Int,
    val userId: Long,
    val isStartedPlaying: Boolean,
    val playlistDto: PlaylistDto
){


    fun toTrackEntity(): TrackEntity {
        return TrackEntity(
            trackName = trackName,
            trackAuthor = artistName,
            trackUrl = trackUrl,
            id = id,
            smallImageUrl = smallImageUrl,
            bigImageUrl = bigImageUrl,
            albumName = albumName,
            artistName = artistName,
            durationInSeconds = durationInSeconds,
            isStartedPlaying = isStartedPlaying,

        )
    }

    fun toUserEntity(): UserEntity {
        return UserEntity(
            id = userId,
            startAnalyticsTime = Timestamp.from(Instant.now()),
        )
    }

}

package com.ilyaboiko.MelismaService.core

import com.ilyaboiko.MelismaService.dto.TrackDto
import com.ilyaboiko.MelismaService.model.TrackEntity

fun TrackEntity.toTrackDto(withListened: Boolean = true): TrackDto {
    return TrackDto(
        id = id,
        trackName = trackName,
        trackUrl = trackUrl,
        smallImageUrl = smallImageUrl,
        bigImageUrl = bigImageUrl,
        albumName = albumName,
        artistName = artistName,
        durationInSeconds = durationInSeconds,
        listenTime = if(withListened) TimeCounter.countTimeInMinutes(statisticsRecords) else null
    )
}

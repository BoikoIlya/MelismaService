package com.ilyaboiko.MelismaService.dto

import java.sql.Timestamp

data class FullStatsDto(
    val totalListeningMinutes: Long,
    val mostPopularPlaylists: List<PlaylistStatsDto>,
    val mostListenedDay: Timestamp,
    val mostListenedDayMinutes: Long
){


    data class PlaylistStatsDto(
        val playlistName:String,
        val listenedMinutes: Long,
    )

}

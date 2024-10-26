package com.ilyaboiko.MelismaService.dto

data class TrackDto(
    val id: String,
    val trackName: String,
    val trackUrl: String,
    val smallImageUrl: String,
    val bigImageUrl: String,
    val albumName: String,
    val artistName: String,
    val durationInSeconds: Int,
    val listenTime: Long? = null,
)

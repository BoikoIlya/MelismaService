package com.ilyaboiko.MelismaService.model

import jakarta.persistence.*

@Entity
@Table(name = "playlists")
data class PlaylistEntity(
    @Id
    val id: String,

    val name: String,

    @OneToMany(mappedBy = "playlist")
    val tracks: List<TrackEntity> = emptyList()
)

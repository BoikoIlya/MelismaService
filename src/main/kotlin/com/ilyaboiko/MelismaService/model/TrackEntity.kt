package com.ilyaboiko.MelismaService.model

import jakarta.persistence.*

@Entity
@Table(name = "tracks")
data class TrackEntity(
    @Id
    val id: String,

    @Column(name = "track_name")
    val trackName: String,

    @Column(name = "track_author")
    val trackAuthor: String,

    @Column(name = "track_url")
    val trackUrl: String,

    @Column(name = "small_image_url")
    val smallImageUrl: String,

    @Column(name = "big_image_url")
    val bigImageUrl: String,

    @Column(name = "album_name")
    val albumName: String,

    @Column(name = "artist_name")
    val artistName: String,

    @Column(name = "duration_in_seconds")
    val durationInSeconds: Int,

    @Column(name = "is_started_playing")
    val isStartedPlaying: Boolean,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity? = null,

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    val playlist: PlaylistEntity? = null,

    @OneToMany(mappedBy = "track", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val statisticsRecords: List<StatisticsRecordEntity> = emptyList()
)



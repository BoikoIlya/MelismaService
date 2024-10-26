package com.ilyaboiko.MelismaService.model

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "statistics_records")
data class StatisticsRecordEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Column(name = "is_started")
    val isStarted: Boolean,

    @Column(name = "record_time")
    val recordTime: Timestamp,


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,

    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    val track: TrackEntity
)

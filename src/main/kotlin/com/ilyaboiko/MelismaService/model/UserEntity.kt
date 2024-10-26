package com.ilyaboiko.MelismaService.model


import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    val id: Long,
    @Column(name = "start_analytics_time")
    val startAnalyticsTime: Timestamp,

    @OneToMany(mappedBy = "user")
    val statisticsRecords: List<StatisticsRecordEntity> = emptyList(),

    @OneToMany(mappedBy = "user")
    val tracks: List<TrackEntity> = emptyList()
)

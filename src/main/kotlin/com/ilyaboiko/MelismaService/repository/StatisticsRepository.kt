package com.ilyaboiko.MelismaService.repository

import com.ilyaboiko.MelismaService.model.StatisticsRecordEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StatisticsRepository: JpaRepository<StatisticsRecordEntity, Long> {


    fun findByTrack_Id(trackId: String): List<StatisticsRecordEntity>
}
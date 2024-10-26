package com.ilyaboiko.MelismaService.controller

import com.ilyaboiko.MelismaService.dto.FullStatsDto
import com.ilyaboiko.MelismaService.dto.TrackDto
import com.ilyaboiko.MelismaService.dto.TrackRecordDto
import com.ilyaboiko.MelismaService.service.StatisticsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/statistics")
class StatisticsController(
    private val statisticsService: StatisticsService
) {

    @PostMapping("/trackRecord")
    fun trackRecord(
        @RequestBody track: TrackRecordDto
    ): ResponseEntity<Void> {
        statisticsService.saveTrackRecord(track)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/all")
    fun statistics(
        @RequestParam("user_id") userId: Long
    ): ResponseEntity<FullStatsDto> {
        return ResponseEntity.ok(statisticsService.getFullStatistics(userId))
    }

    @GetMapping("/mostListenedTracks")
    fun mostListenedTracks(
        @RequestParam("user_id") userId: Long
    ): ResponseEntity<List<TrackDto>> {
        return ResponseEntity.ok(statisticsService.getMostListenedTracks(userId))
    }

    @GetMapping("/listeningHistory")
    fun listeningHistory(
        @RequestParam("user_id") userId: Long
    ): ResponseEntity<List<TrackDto>> {
        return ResponseEntity.ok(statisticsService.getListeningHistory(userId))
    }


}
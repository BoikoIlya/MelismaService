package com.ilyaboiko.MelismaService.service

import com.ilyaboiko.MelismaService.core.TimeCounter
import com.ilyaboiko.MelismaService.core.toTrackDto
import com.ilyaboiko.MelismaService.dto.FullStatsDto
import com.ilyaboiko.MelismaService.dto.TrackDto
import com.ilyaboiko.MelismaService.dto.TrackRecordDto
import com.ilyaboiko.MelismaService.model.StatisticsRecordEntity
import com.ilyaboiko.MelismaService.repository.PlaylistsRepository
import com.ilyaboiko.MelismaService.repository.StatisticsRepository
import com.ilyaboiko.MelismaService.repository.TracksRepository
import com.ilyaboiko.MelismaService.repository.UsersRepository
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant
import kotlin.jvm.optionals.getOrNull

interface StatisticsService {

    fun saveTrackRecord(trackDto: TrackRecordDto)

    fun getFullStatistics(userId: Long): FullStatsDto

    fun getMostListenedTracks(userId: Long): List<TrackDto>

    fun getListeningHistory(userId: Long): List<TrackDto>


    @Service
    class Base(
        private val statisticsRepository: StatisticsRepository,
        private val tracksRepository: TracksRepository,
        private val usersRepository: UsersRepository,
        private val playlistsRepository: PlaylistsRepository
    ) : StatisticsService {


        override fun saveTrackRecord(trackDto: TrackRecordDto) {

            val records = statisticsRepository.findByTrack_Id(trackDto.id)

            if(records.isNotEmpty() && records.last().isStarted == trackDto.isStartedPlaying) return

            var user = usersRepository.findById(trackDto.userId).getOrNull()
            var playlist = playlistsRepository.findById(trackDto.playlistDto.id).getOrNull()
            var track = tracksRepository.findById(trackDto.id).getOrNull()


            if (user == null) user = usersRepository.save(trackDto.toUserEntity())
            if (playlist == null) playlist = playlistsRepository.save(trackDto.playlistDto.toPlaylistEntity())
            if (track == null) track =
                tracksRepository.save(trackDto.toTrackEntity().copy(user = user, playlist = playlist))


            statisticsRepository.save(
                StatisticsRecordEntity(
                    isStarted = trackDto.isStartedPlaying,
                    recordTime = Timestamp.from(Instant.now()),
                    user = user,
                    track = track
                )
            )


        }

        override fun getFullStatistics(userId: Long): FullStatsDto {
            val records = usersRepository.findById(userId).getOrNull()?.statisticsRecords
            val playlists = playlistsRepository.findAll()


            val playlistStats = mutableListOf<FullStatsDto.PlaylistStatsDto>()

            playlists.forEach { playlist ->
                var totalPlaylistTimeListened = 0L
                playlist.tracks.forEach { track ->
                    if(track.statisticsRecords.size>1){
                        totalPlaylistTimeListened += TimeCounter.countTimeInMinutes(track.statisticsRecords)
                    }
                }

                playlistStats.add(
                    FullStatsDto.PlaylistStatsDto(
                        playlist.name,
                        totalPlaylistTimeListened
                    )
                )
            }

            val groupByDates = records?.groupBy { it.recordTime.toLocalDateTime().toLocalDate() }

            var maxDate: Timestamp = Timestamp.from(Instant.now())
            var maxTime = 0L
            groupByDates?.forEach { (_, localRecords) ->
                val totalTime = TimeCounter.countTimeInMinutes(localRecords)
                if (totalTime > maxTime) {
                    maxTime = totalTime
                    maxDate = records.first().recordTime
                }
            }


            return FullStatsDto(
                TimeCounter.countTimeInMinutes(records),
                playlistStats,
                maxDate,
                maxTime,
            )
        }

        override fun getMostListenedTracks(userId: Long): List<TrackDto> {
            val tracks = usersRepository.findById(userId).getOrNull()?.tracks
            return tracks?.map { it.toTrackDto() }?.sortedByDescending { it.listenTime } ?: emptyList()
        }

        override fun getListeningHistory(userId: Long): List<TrackDto> {
            return usersRepository.findById(userId).getOrNull()?.statisticsRecords
                ?.filter { it.isStarted }
                ?.sortedBy { it.recordTime }
                ?.map { it.track.toTrackDto(false) }
                ?: emptyList()
        }


    }

}
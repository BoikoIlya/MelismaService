package com.ilyaboiko.MelismaService.repository

import com.ilyaboiko.MelismaService.model.TrackEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TracksRepository: JpaRepository<TrackEntity, String> {
}
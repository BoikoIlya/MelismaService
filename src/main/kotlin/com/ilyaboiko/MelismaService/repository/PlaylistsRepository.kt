package com.ilyaboiko.MelismaService.repository

import com.ilyaboiko.MelismaService.model.PlaylistEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaylistsRepository: JpaRepository<PlaylistEntity, String> {
}
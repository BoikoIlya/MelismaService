package com.ilyaboiko.MelismaService.dto

import com.ilyaboiko.MelismaService.model.PlaylistEntity

data class PlaylistDto(
    val id: String,
    val name: String
){

    fun toPlaylistEntity(): PlaylistEntity {
        return PlaylistEntity(
            id = id,
            name = name,
        )
    }

}

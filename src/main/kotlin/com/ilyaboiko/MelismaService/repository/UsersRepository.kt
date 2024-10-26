package com.ilyaboiko.MelismaService.repository

import com.ilyaboiko.MelismaService.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository: JpaRepository<UserEntity, Long> {
}
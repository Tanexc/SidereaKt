package ru.tanexc.siderakt.domain.use_case.constellation_use_case

import ru.tanexc.siderakt.domain.repository.ConstellationRepository
import javax.inject.Inject

class GetConstellationById @Inject constructor(
    private val constellationRepository: ConstellationRepository
) {
    suspend operator fun invoke(id: Long) = constellationRepository.getConstellationById(id)
}
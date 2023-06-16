package ru.tanec.siderakt.domain.use_case.constellation_use_case

import ru.tanec.siderakt.domain.repository.ConstellationRepository
import javax.inject.Inject

class GetAllConstellationsUseCase @Inject constructor(
    private val constellationRepository: ConstellationRepository
) {
    operator fun invoke() = constellationRepository.getConstellationList()
}
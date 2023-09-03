package ru.tanexc.siderakt.domain.use_case.constellation_use_case

import ru.tanexc.siderakt.domain.model.Constellation
import ru.tanexc.siderakt.domain.repository.ConstellationRepository
import javax.inject.Inject

class EditConstellationUseCase @Inject constructor(
    private val repository: ConstellationRepository
) {

    suspend operator fun invoke(constellation: Constellation) = repository.editConstellation(constellation)

}
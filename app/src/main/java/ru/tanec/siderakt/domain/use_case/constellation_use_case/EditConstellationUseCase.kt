package ru.tanec.siderakt.domain.use_case.constellation_use_case

import ru.tanec.siderakt.domain.model.Constellation
import ru.tanec.siderakt.domain.repository.ConstellationRepository
import javax.inject.Inject

class EditConstellationUseCase @Inject constructor(
    private val repository: ConstellationRepository
) {

    suspend operator fun invoke(constellation: Constellation) = repository.editConstellation(constellation)

}
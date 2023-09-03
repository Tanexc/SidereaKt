package ru.tanexc.siderakt.domain.use_case.personal_use_case

import ru.tanexc.siderakt.domain.repository.PersonalInfoRepository
import javax.inject.Inject

class GetPersonalInfoUseCase @Inject constructor(
    private val personalInfoRepository: PersonalInfoRepository
) {
    operator fun invoke() = personalInfoRepository.getInfo()
}
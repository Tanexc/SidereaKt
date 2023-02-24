package ru.tanec.siderakt.domain.use_case.personal_use_case

import ru.tanec.siderakt.domain.model.PersonalInformation
import ru.tanec.siderakt.domain.repository.PersonalInfoRepository
import javax.inject.Inject

class SetInfoUseCase @Inject constructor(
    private val personalInfoRepository: PersonalInfoRepository
) {
    suspend operator fun invoke(info: PersonalInformation) = personalInfoRepository.setInformation(info)
}
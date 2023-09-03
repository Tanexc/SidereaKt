package ru.tanexc.siderakt.domain.use_case.personal_use_case

import ru.tanexc.siderakt.domain.model.SettingsData
import ru.tanexc.siderakt.domain.repository.PersonalInfoRepository
import javax.inject.Inject

class SetInfoUseCase @Inject constructor(
    private val personalInfoRepository: PersonalInfoRepository
) {
    suspend operator fun invoke(info: SettingsData) = personalInfoRepository.setInformation(info)
}
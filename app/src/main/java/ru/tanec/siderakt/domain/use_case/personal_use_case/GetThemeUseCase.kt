package ru.tanec.siderakt.domain.use_case.personal_use_case

import ru.tanec.siderakt.domain.repository.PersonalInfoRepository
import javax.inject.Inject

class GetThemeUseCase @Inject constructor(
    private val personalInfoRepository: PersonalInfoRepository
) {
    operator fun invoke() = personalInfoRepository.getTheme()
}
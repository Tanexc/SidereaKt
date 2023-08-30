package ru.tanec.siderakt.domain.use_case.personal_use_case

import ru.tanec.siderakt.domain.interfaces.SettingsController
import ru.tanec.siderakt.domain.model.Constellation
import ru.tanec.siderakt.domain.repository.PersonalInfoRepository
import javax.inject.Inject

class UpdateLearnedConstellationInfoUseCase @Inject constructor(
    private val personalInfoRepository: PersonalInfoRepository,
    private val settingsController: SettingsController
) {

    suspend operator fun invoke(constellations: List<Constellation>) {

        var learnedNorth = 0
        var learnedSouth = 0
        var learnedEquatorial = 0

        for (item in constellations) {
            if (item.learned) {
                learnedNorth += if (item.hemisphere == 1) 1 else 0
                learnedSouth += if (item.hemisphere == 2) 1 else 0
                learnedEquatorial += if (item.hemisphere == 0) 1 else 0
            }
        }

        settingsController.updateSettingsData(
            settingsController.data!!.copy(
                learnedNorth = learnedNorth,
                learnedSouth = learnedSouth,
                learnedEquatorial = learnedEquatorial,
                learnedConstellations = learnedEquatorial + learnedNorth + learnedSouth
            )
        )

        personalInfoRepository.setInformation(settingsController.data!!)

    }

}
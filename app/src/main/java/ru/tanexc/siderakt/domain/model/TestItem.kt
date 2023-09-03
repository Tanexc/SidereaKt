package ru.tanexc.siderakt.domain.model

data class TestItem(
    val constellation: Constellation,
    val answers: List<Constellation>,
    val answer: Constellation? = null
)
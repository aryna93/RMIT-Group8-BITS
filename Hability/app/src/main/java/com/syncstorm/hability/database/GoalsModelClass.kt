package com.syncstorm.hability.database

data class GoalsModelClass (
    var goalID: Int? = null,
    var goalTitle : String? = null,
    var goalDescription: String? = null,
    var goalStartDate: String? = null,
    var goalDifficulty: String? = null,
    var goalCategory: String? = null
)
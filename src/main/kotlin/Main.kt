package com.canorhan

suspend fun main() {
    val skillRequirements =
        listOf(ArmourSkills.BOMBARDIER to 1, ArmourSkills.STAMINA_SURGE to 1, ArmourSkills.FREE_MEAL to 1)

    val minDef = 16

    Solver().solve(skillRequirements, minDef).collect { armourSet ->
        println(armourSet)
    }
}
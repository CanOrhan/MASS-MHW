package com.canorhan

suspend fun main() {
    val skillRequirements = listOf(Skill.CRIT_EYE to 2)
    val minDef = 16

    Solver().solve(skillRequirements, minDef).collect { armourSet ->
        println(armourSet)
    }
}
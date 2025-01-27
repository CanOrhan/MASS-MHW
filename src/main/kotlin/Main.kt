package com.canorhan

import com.canorhan.ArmourSkills.*

suspend fun main() {
    val skillRequirements =
        listOf(DIVINE_BLESSING to 2, SPEED_EATING to 2)

    val minDef = 12

    Solver().solve(skillRequirements, minDef).collect { armourSet ->
        println(armourSet)
    }
}
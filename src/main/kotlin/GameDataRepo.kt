package com.canorhan

import com.canorhan.ArmourSkills.*

data class Armour(
    val name: String,
    val defence: Int,
    val fireRes: Int,
    val waterRes: Int,
    val thunderRes: Int,
    val iceRes: Int,
    val dragonRes: Int,
    val skills: Map<ArmourSkills, Int>
)

class GameDataRepo {
    fun getAllHelms(): List<Armour> = listOf(
        Armour("Balahara Helm", 6, -1, 2, -3, 1, 1, mapOf(BOMBARDIER to 1)),
        Armour("Chatacabra Helm", 6, 1, 2, -3, -1, 1, mapOf(SPEED_EATING to 1)),
        Armour("Doshaguma Helm", 6, -3, 2, -1, -1, 4, mapOf(FREE_MEAL to 1)),
        Armour("Hope Mask", 6, 1, 0, 1, 0, 0, mapOf(STUN_RESISTANCE to 1)),
    )

    fun getAllBodyArmour(): List<Armour> = listOf(
        Armour("Balahara Mail", 12, -1, 2, -3, 1, 1, mapOf(STAMINA_SURGE to 1)),
        Armour("Chatacabra Mail", 12, 1, 2, -3, -1, 1, mapOf(SPEED_EATING to 1)),
        Armour("Doshaguma Mail", 12, -3, 2, -1, -1, 4, mapOf(FREE_MEAL to 1)),
        Armour("Hope Mail", 12, 1, 0, 1, 0, 0, mapOf(DIVINE_BLESSING to 1)),
    )

    fun getAllArm(): List<Armour> = listOf(
        Armour("Balahara Vambraces", 6, -1, 2, -3, 1, 1, mapOf(BOMBARDIER to 1)),
        Armour("Chatacabra Vambraces", 6, 1, 2, -3, -1, 1, mapOf(STAMINA_SURGE to 1)),
        Armour("Doshaguma Vambraces", 6, -3, 2, -1, -1, 4, mapOf(RECOVERY_SPEED to 1)),
        Armour("Hope Vambraces", 6, 1, 0, 1, 0, 0, mapOf(DIVINE_BLESSING to 1)),
    )


}
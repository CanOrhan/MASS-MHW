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

    fun getAllBody(): List<Armour> = listOf(
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

    fun getAllWaist(): List<Armour> = listOf(
        Armour("Balahara Coil", 6, -1, 2, -3, 1, 1, mapOf(BOMBARDIER to 1)),
        Armour("Chatacabra Coil", 6, 1, 2, -3, -1, 1, mapOf(SPEED_EATING to 1)),
        Armour("Doshaguma Coil", 6, -3, 2, -1, -1, 4, mapOf(RECOVERY_SPEED to 1)),
        Armour("Hope Coil", 6, 1, 0, 1, 0, 0, mapOf(STAMINA_SURGE to 1)),
    )

    fun getAllLeg(): List<Armour> = listOf(
        Armour("Balahara Greaves", 6, -1, 2, -3, 1, 1, mapOf(STUN_RESISTANCE to 1)),
        Armour("Chatacabra Greaves", 6, 1, 2, -3, 1, -1, mapOf(RECOVERY_SPEED to 1)),
        Armour("Doshaguma Greaves", 6, -3, 2, -1, 1, -1, mapOf(FREE_MEAL to 1)),
        Armour("Hope Greaves", 6, 1, 0, 1, 1, 0, mapOf(DIVINE_BLESSING to 1)),
    )
}
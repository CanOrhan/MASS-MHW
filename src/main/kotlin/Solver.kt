package com.canorhan

import org.chocosolver.solver.Model
import org.chocosolver.solver.variables.BoolVar
import org.chocosolver.solver.variables.IntVar

import com.canorhan.Skill.CRIT_EYE
import com.canorhan.Skill.QUICK_DRAW
import kotlinx.coroutines.flow.flow

enum class Skill {
    QUICK_DRAW, CRIT_EYE
}

data class Armour(val name: String, val defence: Int, val skills: Map<Skill, Int>)

val body = listOf(
    Armour("Basic Armour 1", 12, mapOf(CRIT_EYE to 1)), Armour("Basic Armour 2", 13, mapOf(QUICK_DRAW to 1))
)

val helms = listOf(
    Armour("Basic Helm 1", 1, mapOf(CRIT_EYE to 1)), Armour("Basic Helm 2", 4, mapOf(CRIT_EYE to 1))
)

data class ArmourSet(val body: String, val helm: String)

class Solver {
    fun solve(skillRequirements: List<Pair<Skill, Int>>, minDef: Int) = flow {
        //Setup
        val model = Model("Metallian's Armour Set Search for Monster Hunter Wilds")
        val labels = listOf("Body", "Helm")
        val armour = listOf(body, helms)
        val armourMap = labels.zip(armour).toMap()
        val armourVarMap = labels.associateWith { mutableListOf<BoolVar>() }
        val defenceVars = mutableListOf<IntVar>()
        val skillToVarList = Skill.entries.associateWith { mutableListOf<IntVar>() }

        //Crunch
        armourMap.forEach { (label, armList) ->
            val armourVars = mutableListOf<BoolVar>()
            armList.forEach { arm ->
                val isSelected = model.boolVar(arm.name)
                armourVars.add(isSelected)
                arm.skills.map { armSkill ->
                    skillToVarList[armSkill.key]!!.add(model.mul(isSelected, armSkill.value))
                }
                defenceVars.add(model.mul(isSelected, arm.defence))
            }
            model.sum(armourVars.toTypedArray(), "<=", 1).run {
                name = "Sum $label armours"
                post()
            }
            armourVarMap[label]!!.addAll(armourVars)
        }

        model.sum(defenceVars.toTypedArray(), ">=", minDef).run {
            name = "Defence sum"
            post()
        }

        skillRequirements.forEach { pair ->
            model.sum(skillToVarList[pair.first]!!.toTypedArray(), ">=", pair.second).run {
                name = "${pair.first} sum"
                post()
            }
        }

        val solver = model.solver
        println("Solving.")
        while (solver.solve()) {
            emit(
                ArmourSet(
                    armourVarMap["Body"]!!.first { it.value != 0 }.name,
                    armourVarMap["Helm"]!!.first { it.value != 0 }.name
                )
            )
        }
        println("")
        println("Fin.")
    }
}
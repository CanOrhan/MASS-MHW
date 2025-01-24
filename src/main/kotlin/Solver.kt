package com.canorhan

import kotlinx.coroutines.flow.flow
import org.chocosolver.solver.Model
import org.chocosolver.solver.variables.BoolVar
import org.chocosolver.solver.variables.IntVar

class Solver(private val gameDataRepo: GameDataRepo = GameDataRepo()) {
    fun solve(skillRequirements: List<Pair<ArmourSkills, Int>>, minDef: Int) = flow {
        //Setup
        val model = Model("Metallian's Armour Set Search for Monster Hunter Wilds")
        val labels = listOf("Body", "Helm", "Arm")
        val body = gameDataRepo.getAllBodyArmour()
        val helms = gameDataRepo.getAllHelms()
        val arms = gameDataRepo.getAllArm()
        val armour = listOf(body, helms, arms)
        val armourMap = labels.zip(armour).toMap()
        val armourVarMap = labels.associateWith { mutableListOf<BoolVar>() }
        val defenceVars = mutableListOf<IntVar>()
        val skillToVarList = ArmourSkills.entries.associateWith { mutableListOf<IntVar>() }

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
                    armourVarMap["Helm"]!!.first { it.value != 0 }.name,
                    armourVarMap["Arm"]!!.first { it.value != 0 }.name
                )
            )
        }
        println("")
        println("Fin.")
    }
}
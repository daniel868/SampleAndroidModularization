package com.example.hero_domain

sealed class HeroAttackType(
    val uiValue: String
) {
    object Melee : HeroAttackType(
        "Melee"
    )

    object Ranged : HeroAttackType(
        "Ranged"
    )

    object Unknown : HeroAttackType(
        "Unknown"
    )

}

fun getHeroAttackType(uiValue: String): HeroAttackType {
    return when (uiValue) {
        HeroAttackType.Melee.uiValue -> {
            HeroAttackType.Melee
        }
        HeroAttackType.Ranged.uiValue -> {
            HeroAttackType.Ranged
        }
        else -> {
            HeroAttackType.Ranged
        }
    }
}


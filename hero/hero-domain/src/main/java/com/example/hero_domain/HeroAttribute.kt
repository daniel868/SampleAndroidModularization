package com.example.hero_domain

sealed class HeroAttribute(
    val uiValue: String,
    val abbreviation: String
) {
    object Agility : HeroAttribute(
        uiValue = "Agility",
        abbreviation = "agi"
    )

    object Strength : HeroAttribute(
        uiValue = "Strength",
        abbreviation = "str"
    )

    object Intelligence : HeroAttribute(
        uiValue = "Intelligence",
        abbreviation = "int"
    )

    object Unknown : HeroAttribute(
        uiValue = "Unknown",
        abbreviation = "unknown"
    )
}

fun getHeroAttrFromAbbreviation(abbreviation: String): HeroAttribute {
    return when (abbreviation) {
        HeroAttribute.Agility.abbreviation -> {
            HeroAttribute.Agility
        }
        HeroAttribute.Strength.abbreviation -> {
            HeroAttribute.Strength
        }
        HeroAttribute.Intelligence.abbreviation -> {
            HeroAttribute.Intelligence
        }
        else -> HeroAttribute.Unknown
    }
}


fun Hero.minAttackDmg(): Int {
    return when (primaryAttribute) {
        is HeroAttribute.Strength -> {
            baseAttackMin + baseStr
        }
        is HeroAttribute.Intelligence -> {
            baseAttackMin + baseInt
        }
        is HeroAttribute.Agility -> {
            baseAttackMin + baseAgi
        }
        is HeroAttribute.Unknown -> {
            0
        }
    }
}

fun Hero.maxAttackDmg(): Int {
    return when (primaryAttribute) {
        is HeroAttribute.Strength -> {
            baseAttackMax + baseStr
        }
        is HeroAttribute.Intelligence -> {
            baseAttackMax + baseInt
        }
        is HeroAttribute.Agility -> {
            baseAttackMax + baseAgi
        }
        is HeroAttribute.Unknown -> {
            0
        }
    }
}

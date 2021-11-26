package com.example.hero_domain

sealed class HeroRole(
    val uiValue: String
) {
    object Carry : HeroRole(
        "Carry"
    )

    object Escape : HeroRole(
        "Escape"
    )

    object Nuker : HeroRole(
        "Nuker"
    )

    object Initiator : HeroRole(
        "Initiator"
    )

    object Durable : HeroRole(
        "Durable"
    )

    object Disable : HeroRole(
        "Disable"
    )

    object Jungler : HeroRole(
        "Jungler"
    )

    object Support : HeroRole(
        "Support"
    )

    object Pusher : HeroRole(
        "Pusher"
    )

    object Unknown : HeroRole(
        "Unknown"
    )
}

fun getHeroRole(uiValue: String): HeroRole {
    return when (uiValue) {
        HeroRole.Carry.uiValue -> {
            HeroRole.Carry
        }
        HeroRole.Escape.uiValue -> {
            HeroRole.Escape
        }
        HeroRole.Nuker.uiValue -> {
            HeroRole.Nuker
        }
        HeroRole.Initiator.uiValue -> {
            HeroRole.Initiator
        }
        HeroRole.Durable.uiValue -> {
            HeroRole.Durable
        }
        HeroRole.Disable.uiValue -> {
            HeroRole.Disable
        }
        HeroRole.Jungler.uiValue -> {
            HeroRole.Jungler
        }
        HeroRole.Support.uiValue -> {
            HeroRole.Support
        }
        HeroRole.Pusher.uiValue -> {
            HeroRole.Pusher
        }
        else -> {
            HeroRole.Unknown
        }
    }
}

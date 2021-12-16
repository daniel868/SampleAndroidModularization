package com.example.modulandroidapp.ui_heroList.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.FilterOrder
import com.example.hero_domain.HeroFilter

@ExperimentalAnimationApi
@Composable
fun HeroListFilter(
    heroFilter: HeroFilter,
    onUpdateHeroFilter: (HeroFilter) -> Unit,
    onCloseDialog: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.padding(16.dp),
        onDismissRequest = {
            onCloseDialog()
        },
        title = {
            Text(
                text = "Filter",
                style = MaterialTheme.typography.h6,
            )
        },
        text = {
            LazyColumn {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        EmptyRow()

                        HeroFilterSelector(
                            filterOnHero = {
                                onUpdateHeroFilter(HeroFilter.Hero())
                            },
                            isEnable = heroFilter is HeroFilter.Hero,
                            order = if (heroFilter is HeroFilter.Hero) heroFilter.order else null,
                            orderDesc = {
                                onUpdateHeroFilter(HeroFilter.Hero(order = FilterOrder.Descending))
                            },
                            orderAsc = {
                                onUpdateHeroFilter(HeroFilter.Hero(order = FilterOrder.Ascending))
                            }
                        )
                    }
                }
            }
        },
        buttons = {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row(modifier = Modifier
                    .align(Alignment.End)
                    .clickable {
                        onCloseDialog()
                    }) {
                    Icon(
                        modifier = Modifier.padding(10.dp),
                        imageVector = Icons.Default.Check,
                        contentDescription = "Done",
                        tint = Color(0xFF009a34)
                    )
                }
            }
        }
    )
}

@ExperimentalAnimationApi
@Composable
fun HeroFilterSelector(
    filterOnHero: () -> Unit,
    isEnable: Boolean,
    order: FilterOrder? = null,
    orderDesc: () -> Unit,
    orderAsc: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    enabled = true,
                    onClick = {
                        filterOnHero()
                    }
                )
        ) {
            Checkbox(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterVertically),
                checked = isEnable,
                onCheckedChange = {
                    filterOnHero()
                },
                colors = CheckboxDefaults.colors(MaterialTheme.colors.primary)
            )
            Text(text = HeroFilter.Hero().uiValue, style = MaterialTheme.typography.h6)
        }
        OrderSelector(
            descString = "z -> a",
            ascString = "a -> z",
            isEnable = isEnable,
            isDescSelected = isEnable && order is FilterOrder.Descending,
            isAscSelected = isEnable && order is FilterOrder.Ascending,
            onUpdateHeroFilterDesc = { orderDesc() },
            onUpdateHeroFilterAsc = { orderAsc() },
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun OrderSelector(
    descString: String,
    ascString: String,
    isEnable: Boolean,
    isDescSelected: Boolean,
    isAscSelected: Boolean,
    onUpdateHeroFilterDesc: () -> Unit,
    onUpdateHeroFilterAsc: () -> Unit
) {
    //descendingOrder
    AnimatedVisibility(visible = isEnable) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, bottom = 8.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    enabled = isEnable,
                    onClick = {
                        onUpdateHeroFilterDesc()
                    }
                )
        ) {
            Checkbox(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterVertically),
                enabled = isEnable,
                checked = isEnable && isDescSelected,
                onCheckedChange = {
                    onUpdateHeroFilterDesc()
                },
                colors = CheckboxDefaults.colors(MaterialTheme.colors.primary)
            )
            Text(text = descString, style = MaterialTheme.typography.body1)
        }
    }

    //ascending order
    AnimatedVisibility(visible = isEnable) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, bottom = 8.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    enabled = isEnable,
                    onClick = {
                        onUpdateHeroFilterAsc()
                    }
                )
        ) {
            Checkbox(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterVertically),
                enabled = isEnable,
                checked = isEnable && isAscSelected,
                onCheckedChange = {
                    onUpdateHeroFilterAsc()
                },
                colors = CheckboxDefaults.colors(MaterialTheme.colors.primary)
            )
            Text(text = ascString, style = MaterialTheme.typography.body1)
        }
    }

}

@Composable
fun EmptyRow() {
    Row(
        modifier = Modifier
            .padding(bottom = 0.dp)
            .fillMaxWidth()
    ) {
        Text(text = "", style = MaterialTheme.typography.h4)
    }
}
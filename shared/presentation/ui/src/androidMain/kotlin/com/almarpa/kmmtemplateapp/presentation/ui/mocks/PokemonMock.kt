package com.almarpa.kmmtemplateapp.presentation.ui.mocks

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.almarpa.kmmtemplateapp.domain.models.Move
import com.almarpa.kmmtemplateapp.domain.models.MoveX
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.models.PokemonDetails
import com.almarpa.kmmtemplateapp.domain.models.Stat
import com.almarpa.kmmtemplateapp.domain.models.StatX
import com.almarpa.kmmtemplateapp.domain.models.TypeX
import com.almarpa.kmmtemplateapp.domain.models.TypeXX
import com.almarpa.kmmtemplateapp.domain.models.enums.PokemonTypeEnum
import com.almarpa.kmmtemplateapp.domain.models.enums.StatNameEnum
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.navigationbar.BottomAppBarItem

fun getPokemonMock() =
    Pokemon(
        id = 1,
        url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        name = "Bulbasaur",
    )

fun getPokemonListMock() =
    listOf(
        Pokemon(
            id = 1,
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
            name = "CHARMELEON",
        ),
        Pokemon(
            id = 2,
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png",
            name = "CHARMELEON CHARMELEON",
        ),
        Pokemon(
            id = 3,
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png",
            name = "Pokemon 3",
        ),
        Pokemon(
            id = 4,
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png",
            name = "Pokemon 4",
        ),
        Pokemon(
            id = 5,
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/5.png",
            name = "Pokemon 5",
        ),
        Pokemon(
            id = 6,
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png",
            name = "Pokemon 6",
        ),
        Pokemon(
            id = 7,
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png",
            name = "Pokemon 4",
        ),
        Pokemon(
            id = 8,
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/8.png",
            name = "Pokemon 5",
        ),
        Pokemon(
            id = 9,
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/9.png",
            name = "Pokemon 6",
        )
    )

fun getPokemonDetailsMock() =
    PokemonDetails(
        id = 1,
        order = 1,
        name = "Bulbasaur",
        baseExperience = 64,
        height = 24,
        weight = 12,
        imageURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        stats = getPokemonStatListMock(),
        types = getPokemonTypeListMock(),
        moves = getPokemonMoveListMock()
    )

fun getPokemonStatListMock() =
    listOf(
        Stat(
            baseStat = 50, effort = 30, statX = StatX(StatNameEnum.ATTACK, "")
        ),
        Stat(
            baseStat = 80, effort = 70, statX = StatX(StatNameEnum.DEFENSE, "")
        ),
        Stat(
            baseStat = 70, effort = 10, statX = StatX(StatNameEnum.SPECIAL_ATTACK, "")
        ),
        Stat(
            baseStat = 100, effort = 30, statX = StatX(StatNameEnum.SPECIAL_DEFENSE, "")
        )
    )

fun getPokemonTypeListMock() =
    listOf(
        TypeX(
            slot = 1, typeXX = TypeXX(PokemonTypeEnum.BUG, "")
        ),
        TypeX(
            slot = 2, typeXX = TypeXX(PokemonTypeEnum.POISON, "")
        )
    )

fun getPokemonMoveListMock() =
    listOf(
        Move(
            move = MoveX(
                name = "Tackle 1", url = "https://pokeapi.co/api/v2/move/33/"
            )
        ),
        Move(
            move = MoveX(
                name = "Tackle 2 Tackle 2", url = "https://pokeapi.co/api/v2/move/33/"
            )
        ),
        Move(
            move = MoveX(
                name = "Tackle 3 Tackle 3 Tackle 3", url = "https://pokeapi.co/api/v2/move/33/"
            )
        ),
        Move(
            move = MoveX(
                name = "Tackle 4 Tackle 4 Tackle 4 Tackle 4",
                url = "https://pokeapi.co/api/v2/move/33/"
            )
        )
    )

@Composable
fun getBottomAppBarItemsMock(): List<BottomAppBarItem> =
    listOf(
        BottomAppBarItem(
            icon = {
                Icon(
                    Icons.AutoMirrored.Outlined.List,
                    contentDescription = "Pokedex",
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
            label = "Pokedex",
            color = MaterialTheme.colorScheme.primary,
            route = Routes.PokemonList,
        ),
        BottomAppBarItem(
            icon = {
                Icon(
                    Icons.Outlined.Person,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Team",
                )
            },
            label = "Team",
            color = MaterialTheme.colorScheme.primary,
            route = Routes.Team,
        )
    )

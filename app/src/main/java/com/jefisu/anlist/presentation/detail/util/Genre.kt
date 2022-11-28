package com.jefisu.anlist.presentation.detail.util

import com.jefisu.anlist.R

fun getGenresImage(genres: List<String>): List<Int> = buildList {
    if (genres.contains("Action")) {
        add(R.drawable.action)
    }
    if (genres.contains("Adult Cast")) {
        add(R.drawable.adult_cast)
    }
    if (genres.contains("Adventure")) {
        add(R.drawable.adventure)
    }
    if (genres.contains("Avant Garden")) {
        add(R.drawable.avant_garden)
    }
    if (genres.contains("Award Winning")) {
        add(R.drawable.award_winning)
    }
    if (genres.contains("Boys Love")) {
        add(R.drawable.boys_love)
    }
    if (genres.contains("Comedy")) {
        add(R.drawable.comedy)
    }
    if (genres.contains("Drama")) {
        add(R.drawable.drama)
    }
    if (genres.contains("Ecchi")) {
        add(R.drawable.ecchi)
    }
    if (genres.contains("Erotica")) {
        add(R.drawable.erotica)
    }
    if (genres.contains("Fantasy")) {
        add(R.drawable.fantasy)
    }
    if (genres.contains("Girls Love")) {
        add(R.drawable.girls_love)
    }
    if (genres.contains("Gourmet")) {
        add(R.drawable.gourmet)
    }
    if (genres.contains("Hentai")) {
        add(R.drawable.hentai)
    }
    if (genres.contains("Horror")) {
        add(R.drawable.horror)
    }
    if (genres.contains("Mystery")) {
        add(R.drawable.mystery)
    }
    if (genres.contains("Romance")) {
        add(R.drawable.romance)
    }
    if (genres.contains("Sci-Fi")) {
        add(R.drawable.sci_fi)
    }
    if (genres.contains("Slice of Life")) {
        add(R.drawable.slice_of_life)
    }
    if (genres.contains("Sports")) {
        add(R.drawable.sports)
    }
    if (genres.contains("Supernatural")) {
        add(R.drawable.supernatural)
    }
    if (genres.contains("Suspense")) {
        add(R.drawable.suspense)
    }
}
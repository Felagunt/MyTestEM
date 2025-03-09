package com.example.mytestem.data.mappers

import com.example.mytestem.data.dto.ButtonDto
import com.example.mytestem.data.dto.OfferDto
import com.example.mytestem.domain.models.Button
import com.example.mytestem.domain.models.Offer

fun OfferDto.toOffer(): Offer {
    return Offer(
        button = button.toButton(),
        id = id,
        link = link,
        title = title
    )
}

fun ButtonDto.toButton(): Button {
    return Button(
        text = text
    )
}
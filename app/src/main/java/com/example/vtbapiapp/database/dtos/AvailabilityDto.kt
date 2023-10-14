package com.example.vtbapiapp.database.dtos

import com.google.gson.annotations.SerializedName

data class AvailabilityDto (
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("wheelchair")
    val wheelchair: PartDto? = null,
    @SerializedName("blind")
    val blind: PartDto? = null,
    @SerializedName("nfc_for_bank_cards")
    val nfc_for_bank_cards: PartDto? = null,
    @SerializedName("qr_read")
    val qr_read: PartDto? = null,
    @SerializedName("supports_usd")
    val supports_usd: PartDto? = null,
    @SerializedName("supports_charge_rub")
    val supports_charge_rub: PartDto? = null,
    @SerializedName("supports_eur")
    val supports_eur: PartDto? = null,
    @SerializedName("supports_rub")
    val supports_rub: PartDto? = null
)
package com.example.vtbapiapp.database.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AvailabilityEntity(
    @PrimaryKey
    val id:Long,
    val wheelchair_cap: Boolean,
    val wheelchair_act: Boolean,
    val blind_cap: Boolean,
    val blind_act: Boolean,
    val nfc_for_bank_cards_cap: Boolean,
    val nfc_for_bank_cards_act: Boolean,
    val qr_read_cap: Boolean,
    val qr_read_act: Boolean,
    val supports_usd_cap: Boolean,
    val supports_usd_act: Boolean,
    val supports_charge_rub_cap: Boolean,
    val supports_charge_rub_act: Boolean,
    val supports_eur_cap: Boolean,
    val supports_eur_act: Boolean,
    val supports_rub_cap: Boolean,
    val supports_rub_act: Boolean,
    val rub_large_bills: Boolean,
    val rub_small_bills: Boolean,
)
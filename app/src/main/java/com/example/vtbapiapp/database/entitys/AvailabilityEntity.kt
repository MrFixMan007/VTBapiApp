package com.example.vtbapiapp.database.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AvailabilityEntity (
    @PrimaryKey
    val id:Long,
    @ColumnInfo(name = "wheelchair_id")
    val wheelchairId:Long,
    @ColumnInfo(name = "blind_id")
    val blindId:Long,
    @ColumnInfo(name = "nfc_for_bank_cards_id")
    val nfcForBankCardsId:Long,
    @ColumnInfo(name = "qr_read_id")
    val qrReadId:Long,
    @ColumnInfo(name = "supports_usd_id")
    val supportsUsdId:Long,
    @ColumnInfo(name = "supports_charge_rub_id")
    val supportsChargeRubId:Long,
    @ColumnInfo(name = "support_eur_id")
    val supportEurId:Long,
    @ColumnInfo(name = "support_rub_id")
    val supportRubId:Long,
)
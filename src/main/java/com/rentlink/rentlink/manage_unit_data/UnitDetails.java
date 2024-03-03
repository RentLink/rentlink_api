package com.rentlink.rentlink.manage_unit_data;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "unit_details", schema = "rentlink")
@Getter
@Setter
class UnitDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;



//    kod do domofonu
//            piętro
//    liczba pięter w budynku
//    winda w budynku tak/nie
//    rodzaj zabudowy: blok / kamienica / apartamentowiec / loft / dom wolnostojący / szeregowiec /
//    okna: plastikowe / drewniane
//    ogrzewanie: miejskie / gazowe / elektryczne / węglowe / własna kotłownia
//    poziom wyposażenia: pełne / puste pokoje / … ? lub checkbox’y: meble pokojowe / meble kuchenne / meble łazienkowe / pralka / zmywarka / lodówka / kuchenka / piekarnik / okap / mikrofalówka / TV / ( (+) dodaj np. ekspres do kawy)
//    media: [checkbox’y] woda zimna / woda ciepła miejska / woda ciepła boiler (piec) / prąd / gaz / internet (+szybkość) / TV
//    informacje dodatkowe [puste pole tekstowe]

}

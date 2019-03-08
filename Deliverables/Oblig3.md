# Oppgave 1

- Har ikke lagt særlig merke til rollene (noe vi tenker er positivt). Gruppen har vært flink til å fordele konkrete oppgaver, dermed har vi ikke hatt særlig behov for at lederen må ta styring. Teamleader - sørger for at alle har noe å gjøre, prosjektet beveger seg framover. Kundekontakt - mest kunnskap om spillet, oversikt, vil etterhvert ha mer direkte kontakt med kunden (dette ble nevnt i en av de siste forelesningene). 
- Ikke noe spesielt som er verdt å nevne. Må bli flinkere til å bryte ned oppgaver i bruker-historier, men ellers er vi fornøyd med valgene vi har tatt. 
- Gruppedynamikken fungerer bra. Det har ikke vært noen konflikter. 
- Det er bra kommunikasjon, vi benytter oss av fysiske møter og slack. Når noen stiller spørsmål for de tidlig svar. Folk møter opp på møter, og om de ikke kan varsels det i fra tidlig. 
- Vi har laget en prosjektsstruktur hvor alle bidrar. Project boardet har vi satt opp og fungert, men burde muligens oppdateres oftere. Møter og project borad har fungert bra for oss. 
- Dette spiller igjen innpå dette med brukerhistorier, enkelte har fått større oppgaver som har krevd mer arbeid. Antall linjer lagt til kan være misvisende for eksempel Tore har en veldig inflatert linjetotal pga uml diagram. Vi skal jobbe videre med å gjøre fordelingen enda jevnere. 
- Referater: 
#### Rapport 12.02.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Tore, Kim-Erling
Siden dette var første møte etter innleveringen var det på tide å fordele nye oppgaver og få oversikt over prosjektet videre. Enkelte krav fant vi
ut var ganske like så videre arbeid krevde nøyere koordinasjon mellom medlemene. 

#### Rapport 19.02.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Tore, Kim-Erling
Det var fokus rundt hvilke krav som var mulig å fullføre til neste innlevering. Utenom dette gikk møtet for det meste ut på å kode. 

#### Rapport 26.02.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Tore
Fikk oblig ut samme dag så det ble dermed ett møte dedikert til gjennomgang av oppgaven. Vi kommuniserte med Kim (bortreist) vha. slack slik at 
han også kunne komme med innspill.

#### Rapport 01.03.19
##### Oppmøte: Jakob, Jan-Erik, Tore
Veldig kort møte ettersom at de fleste hadde mye annet de måtte gjøre. Liten diskusjon om test som ikke fungerte - fikk ikke oppklaring i problemet. 

#### Rapport 05.03.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Tore, Kim-Erling
Fikset problem med tidligere nevnte test. En bug med gameboard ble også rettet på. Ellers var det bare koding. 

#### Rapport 08.03.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Tore, Kim-Erling
Finpusset på innleveringen og gjorde et kort retrospektiv. 

- Punkter:
	- Bruke project boardet oftere og bedre
	- Dele opp oppgaver i mindre brukerhistorier
	- Tester skal fungere før de blir pushet til master
	- Arbeidsmengde skal jevnt fordeles

# Oppgave 2 

Tre av oppgavene var allerede gjort. Vi valgte å få på plass i GUI-en å velge 5 kort. Flagg og backup.

* (Høyest prioritet) Kunne få alle typene bevegelseskort (allerede gjort)
	- generere kort
	- dele ut kort
	- player interaksjon med kortene
* Dele ut 9 kort (allerede gjort)
	- 
* Velge 5 kort (godkjenne valg/si “nå er jeg klar”)
	- 
* Eksekvere program utfra valgte kort
* (Høy prioritet) Besøke flagg
* Hvis robot går av brettet blir den ødelagt og går tilbake til siste backup
* Oppdatere backup hvis robot blir stående på skiftenøkkelrute i slutten av en fase
* Flytte backup ved besøk på flagg
* (Lavere prioritet) Kunne spille en fullverdig runde med alle faser
* Få nye kort til ny runde

- Vi valgte å prioritere gameobjects tidlig, selv om dette ikke sto nevnt - dette var for at alle skulle få noe å gjøre. Interaksjon mellom spilleren og disse objektene ble også implementert slik at vi ikke hadde mye ubrukt kode. 
- Vi verifiserer at kravene er oppfylt vha. JUnit tester og (muligens) manuelle tester. 

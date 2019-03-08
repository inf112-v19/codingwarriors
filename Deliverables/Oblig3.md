#
## Oppgave 1

- Vi har ikke lagt særlig merke til rollene (noe vi tenker er positivt). Gruppen har vært flink til å fordele konkrete oppgaver, dermed har vi ikke hatt særlig behov for at lederen må ta styring. Teamleader - sørger for at alle har noe å gjøre, prosjektet beveger seg framover. Kundekontakt - mest kunnskap om spillet, oversikt, vil etterhvert ha mer direkte kontakt med kunden (dette ble nevnt i en av de siste forelesningene). 
- Ikke noe spesielt som er verdt å nevne. Må bli flinkere til å bryte ned oppgaver i bruker-historier, men ellers er vi fornøyd med valgene vi har tatt. Vi prøver å ha god dokumentasjon og metodenavn slik at det er lett for andre medlemmer å sette seg inn i hverandres kode. Vi har begynt med code reviews, noe som gir oss bedre kunnskap og oversikt over flere deler av programmet, og hvordan helheten fungerer.
- Gruppedynamikken fungerer bra. Det har ikke vært noen konflikter. 
- Det er bra kommunikasjon, vi benytter oss av fysiske møter og slack. Når noen stiller spørsmål, får de tidlig svar. Folk møter opp på møter, og om de ikke kan varsels det i fra tidlig. 
- Vi har laget en prosjektsstruktur hvor alle bidrar. Project-boardet har vi satt opp og fungert, men burde muligens oppdateres oftere. Møter og project borad har fungert bra for oss. 
- Dette spiller igjen innpå dette med brukerhistorier, enkelte har fått større oppgaver som har krevd mer arbeid. Antall linjer lagt til kan være misvisende for eksempel Tore har en veldig inflatert linjetotal pga uml diagram. Vi skal jobbe videre med å gjøre fordelingen enda jevnere. 

#### Retrospektiv
Vi har fått på plass mye funskjonalitet i denne sprinten. Vi må bli bedre på å fullføre oppgavene vi begynner på før vi tar på oss nye oppgaver, samt dele opp oppgavene i mindre biter slik at de blir ferdig i tide. Fokus generelt på møtene kan forbedres slik at effektivitet og produktivitet går opp. En ordstyrer som sørger for at en sak blir tatt opp, snakket om og fullført, før vi går videre på neste sak. Vi er fornøyd med å ha begynt med codereviews, det gir oss mye kunnskap og forståelse om programmet.
- Punkter:
	- Bruke project boardet oftere og bedre
	- Dele opp oppgaver i mindre brukerhistorier
	- Arbeidsmengde skal jevnt fordeles

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
Fikk oblig ut samme dag så det ble dermed ett møte dedikert til gjennomgang av oppgaven. Vi kommuniserte med Kim-Erling (bortreist) vha. slack slik at 
han også kunne komme med innspill.

#### Rapport 01.03.19
##### Oppmøte: Jakob, Jan-Erik, Tore
Veldig kort møte ettersom at de fleste hadde mye annet de måtte gjøre. Liten diskusjon om test som ikke fungerte - fikk ikke oppklaring i problemet. Kim-Erling var fortsatt bortreist.

#### Rapport 05.03.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Tore, Kim-Erling
Fikset problem med tidligere nevnte test. En bug med gameboard ble også rettet på. Ellers var det bare koding. 

#### Rapport 08.03.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Tore, Kim-Erling
Finpusset på innleveringen og gjorde et kort retrospektiv. 


## Oppgave 2 

### Presisering av krav
* Kunne få alle typene bevegelseskort (allerede gjort)
	- generere kort
	- dele ut kort
	- player interaksjon med kortene
* Dele ut 9 kort (allerede gjort)
	- Dele ut 9 kort med forskjellig prioritet
* Velge 5 kort (godkjenne valg/si “nå er jeg klar”) 
	- Vise fem kort på skjerm  (Høy prioritet)
	- Ta input fra spiller  (Høy prioritet)
	- En knapp som godkjenner valg  (Lav prioritet)
* Eksekvere program utfra valgte kort
	- Lage en liste med valgte kort (Høy prioritet)
	- Spiller må ha en flytte-metode som kan eksekvere kort  (Høy prioritet)
	- Lage user input slik at kortene blir utført et av gangen, og ikke alle fem samtidig (Høy prioritet)
* (Høy prioritet) Besøke flagg
	- Brettet sjekker om det er flagg på den posisjonen spilleren er på (Høy prioritet)
	- Sjekk om man står på riktig flagg, hvis ja, legges det til i en liste av besøkte flagg (Høy prioritet)
* Hvis robot går av brettet blir den ødelagt og går tilbake til siste backup
	- Sjekke posisjon spilleren er på, om den er utenfor brettet (Høy prioritet)
	- Lagre backup i spiller (Høy prioritet)
	- Flytte spiller til backup-posisjon (Høy prioritet)
* Oppdatere backup hvis robot blir stående på skiftenøkkelrute i slutten av en fase
	- Vite når en fase er ferdig (Lav prioritet)
	- Lagre og oppdatere backup posisjon i spiller (Høy prioritet)
* Flytte backup ved besøk på flagg
	- Brettet sjekker om det er flagg på den posisjonen spilleren er på (Høy prioritet)
	- Lagre og oppdatere backup posisjon i spiller (Høy prioritet)
* (Lavere prioritet) Kunne spille en fullverdig runde med alle faser
	- Spillerene må få utdelt kort (Høy prioritet)
	- Velge kort (Høy prioritet)
	- Flytte utifra kort (Høy prioritet)
	- Interagere med brettet og objekter (Middels prioritet)
	- Begynne ny runde (Lav prioritet)
* Få nye kort til ny runde
	- Kort fra tidligere runde legges i "discardpile". (Lav prioritet)
	- Nye kort deles ut i fra hovedkortstokk. (Lav prioritet)
	- Når hovedkortstokk er tom flyttes "discardpile" til hovedkortstokk, som da blir stokket og deles ut fra. (Lav prioritet)

### Prioritering av oppgavene
Tre av oppgavene var allerede gjort. Vi valgte å prioritere å få på plass i GUI-en å velge 5 kort, Lage flagg og backup.
Se over for prioriteringer av hvert krav.

### Endring av rekkefølge på krav
- Vi valgte å prioritere flere gameobjects tidlig, da vi var ferdig med kravene fra Oblig1 tidlig, selv om dette ikke sto nevnt - dette var for at alle skulle få noe å gjøre. Interaksjon mellom spilleren og disse objektene ble også implementert slik at vi ikke hadde mye ubrukt kode. 
- Vi verifiserer at kravene er oppfylt vha. JUnit tester der det er mulig å lage tester. Vi har et grafisk grensesnitt som gjør det mulig å spille spillet, slik at man kan teste kravene manuelt.

### Hva vi har gjort
Kunne få alle typene bevegelseskort
Dele ut 9 kort
Velge 5 kort (godkjenne valg/si “nå er jeg klar”)
Eksekvere program utfra valgte kort
Besøke flagg
Hvis robot går av brettet blir den ødelagt og går tilbake til siste backup
Oppdatere backup hvis robot blir stående på skiftenøkkelrute i slutten av en fase
Flytte backup ved besøk på flagg
Få nye kort til ny runde

## Hva vi mangler
Kunne spille en fullverdig runde med alle faser

## Oppgave 4

### Spillinstruksjon
* Spillet kjøres ved å trykke run på RoboRallyMain
* Når spillet starter skal hver spiller velge sine 5 kort ved å klikke på dem i riktig rekkefølge, valgte kort vises i rekkefølge i grønt
* Dersom man angrer på et valg av kort kan man klikke på det samme kortet igjen for å ta det vekk fra valgte kort
* Når det femte kortet velges er det neste spiller sin tur
* Etter at alle spillere har valgt sine kort klikker man et sted på brettet for å utføre instruksjonen på kortene (dette er spillerenes bevegelse)
* Alle spillere utfører sine bevegelser samtidig
* Ved neste klikk på brettet interagerer ting på brettet med spillerne
* Etter at alle fem kort og bevegelser er utført blir det delt ut nye kort
* Spillerene velger så kort på nytt 

* UML-diagram finnes vedlagt i UML-Diagrams mappen i prosjektet. Noen av diagrammene må man klikke "show dependencies" på for å vise avhengigheter.
* Prosjektet importeres som Maven prosjekt, man trenger ikke et working directory


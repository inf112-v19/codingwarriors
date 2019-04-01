# Deloppgave 1
- Testrolle: Jakob
Kode som avhenger mye av Game-klassen vil bli testet manuelt (f.eks laser), mens kode som er mer uavhengig blir testet vha. automatiske tester (f.eks player). Til nå har vi laget mange automatiske tester så teamet har tenkt til å i større grad ta i bruk manuelle tester, noe som ble gjort mot slutten av denne innlevering. Dette var gjort ved å bare spille spillet. I tilfellet for laser testet vi blant annet ved å:
  * Flytte spiller ut av brettet og se at spiller først kommer tilbake etter et runden er over.
  * Skyte på spiller å se at laser-strålen slutter på spiller. 
  * Skyte på spiller å se at en spiller i skuddlinjen bak en vegg eller en annen spiller ikke blir truffet av laseren. 
- Vi har valgt å fortsette med code-reviews og vi har i tillegg prøvd litt mer parprogrammering. Disse førte til kunnskapsoverføring fra den som har mest kjennskap til kodebasen til den som har minst. Det fungerte godt, fordi flere får innsikt til forskjellige deler av spillet og man kan dele den kunnskapen man har opparbeidet seg. Som prosjektmetodikk fungerer en blanding av Scrum og XP bra for oss. Vi fortsetter med sprinter og å bruke prosjekttavlen. 
Som vi har skrevet i sist retroperspektiv så har vi fokusert på at oppgavefordeling, der noen av teammedlemmene som ikke har skrevet så mye kode til nå, har tatt på seg større kodeoppgaver. Det er også mye kunnskapsoverføring på gruppemøtene, der man diskuterer problemer og hjelper hverandre. Vi har også fokusert mer på at alle skal bidra og større oppgaver har nå blitt gitt til personer som har bidratt mindre til kodebasen.
- Vi er fremdeles fornøyd med gruppedynamikken, det har ikke skjedd noen endringer i forhold til dette siden sist. Vi tror ikke den nye testrollen kommer til å endre mye i gruppedynamikken. 
- Kommunikasjonen fungerer fremdeles bra. Vi fortsetter å bruke Slack utenom gruppemøtene og vi merker at Skack blir brukt mye mer nå en i starten av prosjeket. 
- Retrospektiv: 
Vi har fått til kravene vi har satt oss, men enkelte deler av koden kunne hatt godt av å refaktoreres og ønsker å ha større fokus på refaktorering fremover. Vi har støtt på noen feil som ikke ble oppdaget av våres automatiske tester og har begynt å planlegge flere manuelle tester for å lettere kunne plukke opp disse feilene i fremtiden. 
- Forbedringspunkter: 
   - Mer utfyllende manuelle tester
   - Mer fokus på refaktorering
   - Minimer dependencies i koden

# Deloppgave 2
- Vi velger å begynne med å kunne spille en komplett runde. Vi velger dette fordi det er et krav som nesten er fullført, og vi vil gjøre det helt ferdig.
- Lasere er et krav vi har begynt på, så dette får høy prioritet.

# Oppgave 4
Spillinstruksjon:
Dersom man vil spille med en AI, må man legge inn en AI spiller ved å bytte "new Player" til "new AI" i "addPlayers"-metoden i "Game"-klassen. Når AI'en velger kort må man trykke antall ganger den skal velge kort et tilfeldig sted på skjermen.

Spillet kjøres ved å trykke run på RoboRallyMain

Når spillet starter skal hver spiller velge sine 5 kort ved å klikke på dem i riktig rekkefølge, valgte kort vises i rekkefølge i grønt

Dersom man angrer på et valg av kort kan man klikke på det samme kortet igjen for å ta det vekk fra valgte kort

Når det femte kortet velges er det neste spiller sin tur

Etter at alle spillere har valgt sine kort klikker man et sted på brettet for å utføre instruksjonen på kortene (dette er spillerenes bevegelse)

Alle spillere utfører sine bevegelser samtidig

Ved neste klikk på brettet interagerer ting på brettet med spillerne

Etter at alle fem kort og bevegelser er utført blir det delt ut nye kort

Spillerene velger så kort på nytt

UML-diagram finnes vedlagt i UML-Diagrams mappen i prosjektet. Noen av diagrammene må man klikke "show dependencies" på for å vise avhengigheter.

Prosjektet importeres som Maven prosjekt, man trenger ikke et working directory

Det er lasertårn på posisjon (4, 11) som skyter laser (dette er ikke en feil). 

- Møtereferater: 
#### Rapport 12.03.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Tore, Kim-Erling
Vi ble enige om at de som hadde bidrat mindre til kodebasen skulle få større oppgaver (Eirin: AI og Jakob: Laser). 
Det var diskusjon rundt mulige løsninger til disse oppgavene. Kim-Erling begynte på vegger. 

#### Rapport 19.03.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Tore, Kim-Erling
Liten diskusjon angående UML diagram. Eirin og Jan-Erik par-programmerte på AI-en. 

#### Rapport 26.03.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Tore, Kim-Erling
Siden vi fikk den nye obligatoriske oppgaven handlet mye av møte om nettopp denne oppgaven og hvem som skal gjøre hva. 

#### Rapport 29.03.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Kim-Erling
Mye debugging. Diskuterte en del løsninger og forenklinger av kode. 

#### Rapport 01.04.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Tore, Kim-Erling
Finpuss på kode. Støtte på enkelte problemer som måtte fikses på. Hadde i tillegg et retrospektiv.

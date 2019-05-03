# Oblig 5

## Deloppgave 1
  Fra forrige gang har vi valgt å fokusere på å skrive mer utfyllende manuelle tester som gjør det lettere å endre og teste koden etterpå.
  Vi har også hatt mer fokus på å refaktorere koden, slik at den blir lettere å lese og endre for alle. Hadde hatt godt av mer refaktorering, men vi har rettet en del av koden, som f. eks valg av kort, som gjør koden mye lettere å lese og forstå. Refaktorering ble mer viktig etterhvert som kodebasen økte og det var vanskeligere å holde styr på all kode.
  Manuelle tester kan gjøre det lettere å teste hele koden, også deler som er vanskelig å teste. Greit å ha en liste med ting som skal testes. Vi har lært at manuelle tester er viktig for å teste helheten i koden.
  Kunne hatt jevnere arbeidsflyt i løpet av prosjektet, vi har hatt flere store innleveringer i andre fag som har påvirket arbeidsflyten. Men alt i alt er vi fornøyd med hvordan teamet har fungert sammen. 
  Vi begynte prosjektet uten en veldig klar plan på prosjektmetodikk. Vi startet med å fordele oppgave, da ble noen oppgaver veldig store og andre veldig små, som gjorde arbeidsfordelingen litt skjev. Etterhvert har vi fått mer samarbeid og kunnskapsoverføring gjennom codereviews, og forsøkt parprogrammering. Bra kommunikasjon og hjelp mellom medlemmer både på slack og fysiskegruppemøter.
  Fra XP har vi brukt flere elementer, blandt annet: Kundekontakt, korte sykluser, vi har brukt parprogrammering, prøvd TDD men valgt å ikke fortsette med, refaktorering.
  Fra scrum har vi brukt project board, som har vært nyttig når vi etter hvert i prosjektet brukte den mer aktivt for å planlegge og fordele oppgaver, slik at det ikke blir overlapp mellom oppgaver i teamet.

- Gruppedynamikken har ikke forandret seg siden sist, den er fortsatt bra. Testrollen har ikke påvirket gruppedynamikken, som var det vi trodde sist.

- Kommunikasjonen foregår direkte via gruppemøter og ellers via slack. Det er mye kunnskapsoverføring på gruppemøtene, der man diskuterer problemer og hjelper hverandre.  

- Retrospektiv
Vi har underveis delt opp oppgavene i mindre deler for at alle på gruppen skulle ha omtrent lik arbeidsmengde.
Hyppigere kommunikasjon på slack underveis i prosjektet gjorde at det ble lettere å kommunisere og dele ut oppgaver.
Underveis brukte vi slack mer til å diskutere kode og 
Vi lærte mye av codereviews, vanskelig å opprettholde fokus på det når det nærmet seg fristen.
Ikke så mye parprogrammering da vi har veldig forskjellige timeplaner. Fikk parprogrammert når vi hadde fysiske gruppemøter.
Vi var veldig uerfarne på git i starten, men tryggheten økte underveis i prosjektet.
I prosjektet har vi jobbet mye individuelt, men med samarbeid på gruppemøter og slack.
Det som har fungert best er at alle har fått konkrete arbeidsoppgaver som er delt opp i små nok deler til at det ikke virker massivt og uoverkommelige. Når alle fikk tildelt oppg de følte var gjennomførbare fikk vi gjort mest i hver sprint. 
Hvis vi skulle fortsatt på prosjektet så ville vi hatt jevnere kodeflyt, og hatt mer codereviews.
Vi har lært mye om kommunikasjon. Det har vært viktig å snakke sammen slik at ingen jobber på samme oppgave, og at man tenker over at det man sier og det en annen hører ikke nødvendigvis er det samme. 
- Møtereferater: 
#### 02.04.19
##### Oppmøte: Jakob, Eirin, Tore, Kim-Erling
Tore hadde vært på kunde-møte og dermed snakket vi om de kravene som kom fra det møtet. 

#### 09.04.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Tore, Kim-Erling
Skaffet et overblikk over hva som manglet fra MVP som skal implementeres til siste innlevering. Etter at oppgaver var fordelt
gikk resten av møte ut på koding. 

#### 23.04.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Tore, Kim-Erling
Snakket en del om nettverk og hvordan dette skulle implementeres i prosjektet. Det ble ikke noe tilfredstillende svar på dette 
spørsmålet. 

#### 30.04.19
##### Oppmøte: Jakob, Jan-Erik, Eirin, Tore, Kim-Erling
Jan-Erik og Tore par-programmerte på refaktorering av valg av kort. Teamet fikk et lite overblikk over bugs og mangler til siste innlevering. 

#### 03.05.19
##### Oppmøte: Jakob, Jan-Erik, Eirin
Fullført power down funksjonalitet. Løst en rar feil i forbindelse med power down og bevegelse av spilleren.

## Deloppgave 2
- Liste over alle krav som er ferdigstilt:
- Man må kunne spille en komplett runde
- Man må kunne vinne spillet spillet ved å besøke siste flagg (fullføre et spill)
- Det skal være lasere på brettet
- Det skal være hull på brettet
- Skademekanismer (spilleren får færre kort ved skade)
- Spillmekanismer for å skyte andre spillere innen rekkevidde med laser som peker rett frem
- fungerende samlebånd på brettet som flytter robotene
- fungerende gyroer på brettet som flytter robotene
- game over etter 3 tapte liv
- power down
- samlebånd som går i dobbelt tempo
- spille mot AI (single-player-mode), evt spill mot random-roboter


## Deloppgave 3

Kjente feil og svakheter:
- Vi har forsøkt å implementere nettverk, men det ble litt for lite tid til at den ble helt fullført. Koden er derfor ikke med i innleveringen.
- Respawningen er en svakhet når to spiller respawner på samme plass. Vi spør ikke spiller om hvilken vei de vil settes ut.
- Victory og Game Over skjerm har blitt litt enklere enn først tenkt. 


Hvis man går til mappen i prosjektet kan man kjøre "mvn test" fra terminalen.
For å kjøre alle tester gå til test mappen og høyre-klikk på mappen som inneholder alle klassene og "Run Tests in ...". Manuelle tester ligger i hovedmappen på prosjektet, ved navn manuelle tester.
Spillinstruksjon: Dersom man vil spille med en AI, må man legge inn en AI spiller ved å bytte "new Player" til "new AI" i "addPlayers"-metoden i "Game"-klassen.

Spillet kjøres ved å trykke run på RoboRallyMain

Når spillet starter skal hver spiller velge sine 5 kort ved å klikke på dem i riktig rekkefølge, valgte kort vises i rekkefølge i fargen til den aktive spilleren.

Dersom man angrer på et valg av kort kan man klikke på det samme kortet igjen for å ta det vekk fra valgte kort

Når det siste kortet velges kan man trykke confirm cards

Etter at alle spillere har valgt sine kort klikker man et sted på brettet for å utføre instruksjonen på kortene (dette er spillerenes bevegelse)

Alle spillere utfører sine bevegelser samtidig

Ved neste klikk på brettet interagerer ting på brettet med spillerne

Etter at alle fem kort og bevegelser er utført blir det delt ut nye kort

Spillerene velger så kort på nytt

UML-diagram finnes vedlagt i UML-Diagrams mappen i prosjektet. Noen av diagrammene må man klikke "show dependencies" på for å vise avhengigheter.

Prosjektet importeres som Maven prosjekt, man trenger ikke et working directory

Det er lasertårn på posisjon (4, 11) som skyter laser (dette er ikke en feil).

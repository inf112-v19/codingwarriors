# Obligatorisk øvelse 2: Robo Rally

## Deloppgave 1
* Gruppenavn: Codingwarriors
* Gruppeleder: Jan-Erik Erstad
* Kundekontakt: Tore Berven

### Kompetanse
Oppsummering: 
Alle på teamet har hatt INF100, INF101, INF102 og har dermed de grunnleggende programmeringskunnskapene i Java, og erfaring med objektorientert programmering.
I tillegg har teamet en blanding av forskjellige studier (datateknologi, datasikkerhet) og fagkombinasjoner som gir bredde i teamet.  

### Tekniske verktøy
* Github project board
* Slack
* Git
* Java (IntelliJ)

## Deloppgave 2
### Beskrivelse av mål
Det overordnede målet for applikasjonen er å implementere et Multiplayer Robo Rally spill, som kan spilles av 2-8 spillere lokalt og over lan.
Vi gjør dette med en objektorientert tilnærming, og bruker ulike verktøy som hjelper oss til å skrive en robust kode som er enkel å oppdatere og utvide.

### Liste over krav
* Ha et Brett
    * Vegger kan ikke gåes gjennom
    * Laser gjør skade
    * Rullebånd flytter spiller
    * Hull tar et liv og starter spilleren fra forrige backup
    * Skiftenøkkel
    * Tannhjul roterer spiller
    * Backup lagres og oppdateres for hver spiller
    * Flagg
    * Robot som representerer spilleren
* Spiller
    * Helse
    * Posisjon på brettet
    * Retning
    * Flytte ut i fra kort
    * Registrere flagg
    * Kan ta powerdown
* Kort 
    * Prioritet
    * Action
* Gameloop
    * Dele ut kort
    * Velge kort og plass 1-5
    * Snu kort
    * Flytte ut i fra prioritet
    * Utføre Actions fra objekter på brettet
    

### Prioritert liste over det som skal med i første iterasjon
1. Et spillbrett
2. En spiller som kan bevege seg på bretter
3. GUI som kan vise disse
4. Kort
5. 

## Deloppgave 3
Som prosjektmetodikk har vi valgt kombinere elementer fra Scrum og XP. 
Vi setter opp en prosjekttavle der hvert teammedlem jobber med en arbeidsoppgave om gangen. Der har vi kontroll på hva alle jobber med til enhver tid.
Tildeling av oppgaver skjer på prosjekttavlen og diskuteres på møter. 
For å fungere godt sammen som gruppe skal vi dele kompetanse og ha klare planer for hver iterasjon som gjør at alle får bidratt.
Koden skal testes, i første omgang med enhetstester, gjerne av andre medlemmer på teamet enn den som har skrevet koden.
Vi planlegger møter to ganger i uken der vi følger opp hverandres arbeid, diskuterer arbeidsoppgaver.
Ellers kommuniserer vi over slack. 
Etter hver iterasjon gjennomgås og vurderes hvordan gruppen har jobbet og erfaringer man har opparbeidet seg, for å komme med endringer og bedre løsninger til neste iterasjon.


## Oppsummering
#### Hva gikk bra? 
Alle har bidratt og vi kom tidlig i gang med å kode. I tillegg har det vært god kommunikasjon i gruppen. 
#### Hva fungerte ikke helt som forventet?
Det ble laget mye kode, men foreløpig er det mye som ikke fungerer sammen. Vi tenker vi muligens burde laget mindre kode som fungerte, og gradvis lagt til den fungerende koden (litt som TDD).
#### Noe vi har lyst å prøve til neste øvelse
Vi ønsker å prøve mer par-programmering. I tillegg ønsker vi å prøve mer test driven development, som nevnt over tror vi dette ville vært en tilnærming som kunne fungert bedre for oss. 

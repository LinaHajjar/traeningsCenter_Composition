# Træningscenter: fra én stor klasse til komposition og nedarving

Denne opgave løber over hele ugen **Genbrug med komposition og nedarving**.

- I **trin 1** skriver du et administrationssystem til et træningscenter med det, du allerede kan.
- I **trin 2** bygger du programmet om, skridt for skridt, så det bruger komposition (**has-a**) og nedarving (**is-a**).

Målet er, at du selv mærker *hvorfor* vi har brug for de nye begreber, før du lærer dem.

### Læringsmål for ugen

**Komposition**
i slutningen af ugen kan du:
- referere fra én klasse til en instans af en anden klasse
- genbruge kode i stedet for at gentage den
- splitte din kode ud i relevante klasser, der gør brug af hinanden
- afgøre, om der er en has-a- eller en is-a-relation mellem to klasser

**Nedarving**
i slutningen af ugen kan du:
- bruge keywordet `extends` til at nedarve fra en klasse
- bruge keywordet `super` til at kalde konstruktøren på din superklasse
- bruge keywordet `abstract` til at gøre klasser og metoder abstrakte
- forklare, hvad det betyder, at en klasse er abstrakt
- override metoder fra superklassen i din subklasse

### Filer i projektet

| Fil | Indhold |
|---|---|
| `src/ClassIntroduction.java` | Løsning på trin 1: hele programmet i én klasse |
| `src/composition/` – `TrainingSession.java`, `Booking.java`, `Member.java`, `BasicMember.java`, `PremiumMember.java`, `FitnessCenter.java`, `Main.java` | Løsning på trin 2 |
| `Klassediagram.md`, `Klassediagram.svg` | Klassediagram for løsningen på trin 2 |

---

# Trin 1: Træningscenteret i én klasse

Et træningscenter vil have et lille Java-program, der kan holde styr på medlemmer, træningstimer og bookinger.

Du må **kun** bruge det, du allerede kender:

- variabler og datatyper (`String`, `int`, `double`, `boolean`)
- `if` / `else if` / `else`
- `for`-løkker og `for-each`
- metoder med parametre og returværdier
- `ArrayList`
- `Scanner` til at læse input fra brugeren
- én klasse med felter, konstruktør og metoder

Hele programmet skal ligge i **én klasse**, der hedder `ClassIntroduction`, med en `main`-metode, der starter programmet.

### Krav

**Medlemmer**
1. Et medlem har et navn, et medlemsnummer og en medlemstype.
2. Der findes to medlemstyper: `"Basic"` og `"Premium"`.
3. Et Basic-medlem betaler 199 kr. om måneden og må højst have 2 aktive bookinger.
4. Et Premium-medlem betaler 349 kr. om måneden og må højst have 5 aktive bookinger.
5. Prøver man at oprette et medlem med en ukendt type, skal det afvises med en besked.

**Træningstimer**
6. En træningstime har en titel, et instruktørnavn og et maksimalt antal pladser.
7. Programmet skal kunne udskrive alle træningstimer med antal tilmeldte og antal ledige pladser.
8. Programmet skal kunne udskrive kun de træningstimer, der har ledige pladser.

**Bookinger**
9. Et medlem kan booke en træningstime, men **kun** hvis
   - medlemmet ikke allerede har booket samme time,
   - timen har en ledig plads, og
   - medlemmet ikke har nået sit maksimale antal aktive bookinger.
10. Et medlem kan afmelde en booking. Pladsen skal så blive ledig igen.
11. Programmet skal kunne vise et medlems aktive bookinger.

**Brugergrænseflade**
12. Programmet skal have en menu i konsollen, hvor brugeren kan vælge alle funktionerne ovenfor, fx:

```text
===== PowerGym =====
1. Vis alle medlemmer
2. Vis alle træningstimer
3. Vis træningstimer med ledige pladser
4. Opret medlem
5. Opret træningstime
6. Book en træningstime
7. Afmeld en træningstime
8. Vis et medlems aktive bookinger
9. Kør testscenariet
0. Afslut
Vælg:
```

13. Menuen skal blive ved med at blive vist, indtil brugeren vælger `0`.
14. Programmet må ikke gå ned, hvis brugeren skriver bogstaver, hvor der forventes et tal.
15. Når programmet starter, skal der allerede være nogle medlemmer og træningstimer, så man kan prøve det med det samme.

**Udskrifter**

```text
Bookingen er gennemført.
Sara er nu tilmeldt Yoga.
```

```text
Bookingen blev afvist.
Sara må højst have 2 aktive bookinger.
```

### Hint

Du har ingen `Member`-klasse og ingen `TrainingSession`-klasse. Hvordan gemmer du så tre oplysninger om ét medlem?
Et bud er at bruge flere lister, hvor **samme index** hører til det samme medlem:

```java
private ArrayList<String> memberNames;
private ArrayList<Integer> memberIds;
private ArrayList<String> memberTypes;
```

### Testscenarie (menupunkt 9)

Lav en metode, der opretter et nyt center med mindst 2 Basic-medlemmer, 1 Premium-medlem og 3 træningstimer, hvoraf mindst én kun har 1–2 pladser. Test at

1. begge medlemstyper kan booke,
2. en fuld træningstime afviser flere bookinger,
3. et medlem ikke kan booke samme time to gange,
4. et medlem ikke kan overskride sin bookinggrænse,
5. aktive bookinger kan vises,
6. en booking kan annulleres,
7. den ledige plads kommer tilbage efter en annullering.

### Refleksion efter trin 1

Kig på din egen løsning (eller `ClassIntroduction.java`) og svar på spørgsmålene:

1. Hvor mange felter har klassen? Hvor mange metoder?
2. Hvor mange lister skal du ændre, når du opretter **én** booking? Hvad sker der, hvis du glemmer én af dem?
3. Centret vil have en ny medlemstype, `"Student"`, der betaler 149 kr. og må have 3 bookinger. Hvor mange steder i koden skal du rette?
4. Hvad sker der, hvis nogen skriver `"premium"` med lille p?
5. En ny udvikler skal rette en fejl i bookingreglerne. Hvor let er det at finde det rigtige sted?
6. Hvor meget af klassen handler om menuen og input, og hvor meget handler om selve træningscenteret? Hører det sammen?
7. Hvilke **navneord** går igen i opgaveteksten? (Medlem, træningstime, …)

> Programmet virker – men det bliver hurtigt uoverskueligt. Én klasse har alt for mange ansvarsområder.
> Det løser vi i trin 2.

---

# Trin 2: Del programmet op

Læs **has-a.md** inden du går i gang med 2.1–2.6, og **is-a.md** inden du går i gang med 2.7–2.11.

Start et nyt sæt klasser ved siden af `ClassIntroduction`. Behold `ClassIntroduction` uændret, så du kan sammenligne til sidst.

## Del A: Komposition (has-a)

### 2.1 Find ansvarsområderne

Tag listen med navneord fra refleksionsspørgsmål 7.

- Hvilke navneord har deres **egne data**? Det er kandidater til klasser.
- Skriv for hver kandidat: *Hvad skal klassen vide?* (felter) og *Hvad skal den kunne?* (metoder).

**Spørgsmål:** Hvilke felter i `ClassIntroduction` hører til et medlem, og hvilke hører til en træningstime?

### 2.2 Klassen `TrainingSession`

Flyt alt, der handler om én træningstime, ud i sin egen klasse.

```java
private String title;
private String instructor;
private int capacity;
```

Tilføj metoderne

```java
public boolean hasAvailableSpace()
public int getAvailableSpaces()
public void printSession()
```

**Spørgsmål:** I `ClassIntroduction` havde du fire lister om træningstimer. Hvor mange lister om træningstimer skal du bruge nu?

### 2.3 Klassen `Member`

Lav en klasse `Member` med felterne `name`, `memberId` og `type` (behold typen som `String` indtil videre).
Flyt `getMaxBookings()` og `getMonthlyPrice()` over i `Member`. Bemærk, at de ikke længere behøver en parameter – hvorfor ikke?

### 2.4 Den første has-a: en træningstime har deltagere

Tilføj et felt til `TrainingSession`:

```java
private ArrayList<Member> participants;
```

Nu **refererer** en `TrainingSession` til instanser af en anden klasse. Det er komposition:

```text
TrainingSession HAS-A Member
```

Lav metoderne

```java
public boolean addParticipant(Member member) // kun hvis der er plads
public boolean removeParticipant(Member member)
```

**Spørgsmål:**
- Hvor skal listen `participants` oprettes med `new`? Hvad sker der, hvis du glemmer det?
- I `ClassIntroduction` talte vi deltagere med et `int`. Hvad kan vi nu, som vi ikke kunne før?

### 2.5 Klassen `Booking`: et objekt, der binder to andre sammen

En booking forbinder et medlem med en træningstime.

```java
private Member member;
private TrainingSession trainingSession;
private boolean active;
```

```text
Booking HAS-A Member
Booking HAS-A TrainingSession
```

Lav metoderne `cancel()`, `isActive()` og `printBooking()`.
Når en booking annulleres, skal medlemmet også fjernes fra træningstimens deltagerliste.

Giv derefter `Member` en liste over bookinger:

```java
private ArrayList<Booking> bookings;
```

Lav `addBooking()`, `getActiveBookingCount()` og `printBookings()` i `Member`.

**Spørgsmål:**
- I has-a.md kan et objekt enten *oprettes i konstruktøren* eller *modtages som parameter*. Hvilken måde bruger `Booking` for sine to felter? Hvorfor giver det mening her?
- `cancel()` kalder en metode på `trainingSession`. Hvorfor er det bedre, end at `FitnessCenter` selv fjerner medlemmet?
- Sammenlign med `ClassIntroduction`: hvor mange lister skulle ændres for at oprette én booking før, og hvor mange nu?

### 2.6 Klassen `FitnessCenter`

```java
private String name;
private ArrayList<Member> members;
private ArrayList<TrainingSession> sessions;
```

Lav metoderne

```java
public void addMember(Member member)
public void addSession(TrainingSession session)
public boolean bookSession(Member member, TrainingSession session)
public boolean cancelBooking(Member member, TrainingSession session)
public void printAllMembers()
public void printAllSessions()
public void printAvailableSessions()
```

Flyt testene fra `ClassIntroduction.main` over i en ny `Main`-klasse og få dem til at køre.

**Spørgsmål:** `bookSession` tager nu et `Member`-objekt i stedet for et medlemsnummer. Hvilke hjælpemetoder fra `ClassIntroduction` (fx `findMemberIndex`) har du ikke længere brug for? Hvorfor?

### Opsamling: has-a eller is-a?

Afgør for hvert par, om det er **has-a**, **is-a** eller ingen af delene:

| Par | has-a / is-a? |
|---|---|
| `FitnessCenter` – `Member` | |
| `Booking` – `TrainingSession` | |
| Premium-medlem – medlem | |
| `Member` – `Booking` | |
| Yogatime – træningstime | |
| Instruktør – person | |
| `TrainingSession` – instruktør | |

---

## Del B: Nedarving (is-a)

### 2.7 Problemet med `type`

Kig på `getMaxBookings()` og `getMonthlyPrice()` i `Member`:

```java
if (type.equals("Basic")) {
    return 2;
} else if (type.equals("Premium")) {
    return 5;
}
```

**Spørgsmål:**
- Hvor mange `if`-sætninger skal du rette, når `"Student"` kommer til?
- Et Basic-medlem og et Premium-medlem har meget til fælles. Hvad er fælles, og hvad er forskelligt?

### 2.8 `extends` og `super`

Lav to subklasser:

```java
public class BasicMember extends Member
public class PremiumMember extends Member
```

```text
BasicMember   IS-A Member
PremiumMember IS-A Member
```

Fjern feltet `type` fra `Member`. Konstruktøren i subklasserne skal kalde superklassens konstruktør:

```java
public BasicMember(String name, int memberId) {
    super(name, memberId);
}
```

**Spørgsmål:**
- Hvad sker der, hvis du fjerner linjen `super(name, memberId);`? Prøv det, og læs fejlbeskeden.
- `BasicMember` har ingen `getName()`-metode. Hvorfor kan du alligevel kalde `sara.getName()`?

### 2.9 `@Override`

Override de to metoder i hver subklasse:

```java
@Override
public int getMaxBookings() {
    return 2;
}

@Override
public double getMonthlyPrice() {
    return 199;
}
```

Nu kan alle `if (type.equals(...))` slettes.

**Spørgsmål:** Stav med vilje forkert, fx `getMaxBooking()`, én gang **med** `@Override` og én gang **uden**. Hvad er forskellen? Hvorfor er `@Override` nyttig?

### 2.10 `abstract`

Giver det mening at skrive dette?

```java
Member member = new Member("Sara", 101);
```

Hvilken pris skal sådan et medlem betale? Hvor mange bookinger må det have? Der findes ikke "bare et medlem" – man er altid Basic eller Premium.

- Gør `Member` til en **abstrakt klasse**: `public abstract class Member`
- Gør de to metoder **abstrakte**: `public abstract int getMaxBookings();` (uden krop)

**Spørgsmål:**
- Hvad sker der nu med linjen `new Member("Sara", 101)`?
- Hvad sker der, hvis `PremiumMember` glemmer at override `getMonthlyPrice()`?
- Forklar med dine egne ord, hvad det betyder, at en klasse er abstrakt.

### 2.11 Polymorfi

Læg mærke til typerne i `Main` og `FitnessCenter`:

```java
Member sara = new BasicMember("Sara", 101);
Member ali = new PremiumMember("Ali", 102);

ArrayList<Member> members; // i FitnessCenter
```

**Spørgsmål:**
- Variablen `sara` har typen `Member`. Hvordan ved Java, at `sara.getMaxBookings()` skal returnere 2?
- `bookSession()` i `FitnessCenter` ved ikke, om medlemmet er Basic eller Premium. Hvorfor virker bookinggrænsen alligevel?

### 2.12 Den store test: `StudentMember`

Tilføj medlemstypen `StudentMember` (149 kr., højst 3 bookinger) og test den i `Main`.

**Spørgsmål:**
- Hvor mange filer skulle du ændre eller oprette?
- Sammenlign med dit svar på refleksionsspørgsmål 3 fra trin 1. Hvad er forskellen?

### 2.13 Klassediagram

Tegn et klassediagram over dit program. Vis alle has-a-relationer og is-a-relationer, og marker den abstrakte klasse og de abstrakte metoder.
Sammenlign bagefter med `Klassediagram.md`.

---

## Ekstra: Pænere udskrift

Brug **Unicode Cheatsheet** til at gøre udskrifterne pænere, fx:

```java
System.out.println("✓ Bookingen er gennemført.");   // ✓
System.out.println("✘ Bookingen blev afvist.");     // ✘
```

- Lav en overskrift med box-tegn (`╔ ═ ╗ ║ ╚ ╝`) til `printAllSessions()`.
- Brug ANSI-farver: grøn for gennemført og rød for afvist. Husk `\u001B[0m` til sidst.
- **Genbrug:** Hvor bør metoderne `printSuccess()` og `printError()` ligge, så alle klasser kan bruge dem uden at kopiere koden?

---

## Afslutning: Sammenlign de to løsninger

| | Trin 1 (`ClassIntroduction`) | Trin 2 |
|---|---|---|
| Antal klasser | 1 | |
| Hvor ligger bookingreglerne? | | |
| Steder at rette for en ny medlemstype | | |
| Kan man oprette en ugyldig medlemstype? | | |
| Hvilken er nemmest at læse? | | |

Udfyld tabellen, og vær klar til at forklare dine svar ved fredagens opsamling.

## Kør programmet

Åbn projektet i IntelliJ, og kør `main` i enten `ClassIntroduction` (trin 1) eller `composition.Main` (trin 2).
`ClassIntroduction` starter en menu i konsollen. Vælg `9` for at køre det samme testscenarie, som `composition.Main` kører.

# Træningscenter: fra én stor klasse til komposition og nedarving

Denne opgave løber over hele ugen **Genbrug med komposition og nedarving**.

- I **trin 1** har jeg bygget et administrationssystem til et træningscenter med det, vi allerede har lært.
- I **trin 2** skal vi bygge programmet om, skridt for skridt, 
så det bruger komposition (**has-a**) og nedarving (**is-a**).

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

| Fil                                                                                                                                                    | Indhold |
|--------------------------------------------------------------------------------------------------------------------------------------------------------|---|
| `src/ClassIntroduction.java`                                                                                                                           | Løsning på trin 1: hele programmet i én klasse |
| `src/composition/UI.java`                                                                                                                              | Færdig menu og input til trin 2. Udfyld TODO'erne, efterhånden som du laver klasserne |
| `src/composition/` – `TrainingSession.java`, `Booking.java`, `Member.java`, `BasicMember.java`, `PremiumMember.java`, `FitnessCenter.java`, `Main.java` | Løsning på trin 2 |
| `src/diagrams`                                                                                                                   | Klassediagram for løsningen på trin 2 |

---

# Trin 1: Træningscenteret i én klasse

Et træningscenter vil have et lille Java-program, der kan holde styr på medlemmer, træningstimer og bookinger.

under: C:\Users\linah\IdeaProjects\traeningsCenter_Composition\src\ClassIntroduction.java
er kodet skrevet **kun** i én klasse:

- variabler og datatyper (`String`, `int`, `double`, `boolean`)
- `if` / `else if` / `else`
- `for`-løkker og `for-each`
- metoder med parametre og returværdier
- `ArrayList`
- `Scanner` til at læse input fra brugeren
- én klasse med felter, konstruktør og metoder

Hele programmet ligger i **én klasse**, der hedder `ClassIntroduction`, med en `main`-metode, der starter programmet.

### Krav

**Medlemmer**
1. Et medlem har et navn, et medlemsnummer og en medlemstype.
2. Der findes to medlemstyper: `"Basic"` og `"Premium"`.
3. Et Basic-medlem betaler 199 kr. om måneden og må højst have 2 aktive bookinger.
4. Et Premium-medlem betaler 349 kr. om måneden og må højst have 5 aktive bookinger.
5. Test: Prøver man at oprette et medlem med en ukendt type, skal det afvises med en besked.

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

### Testdata

Når programmet starter, skal der allerede være testdata, så man kan prøve menuen med det samme: mindst 2 Basic-medlemmer, 1 Premium-medlem og 3 træningstimer, hvoraf mindst én kun har 1–2 pladser.
Programmet skal starte direkte med menuen. Testdataene vises først, når brugeren vælger at se medlemmer eller træningstimer.

# Refleksion efter trin 1:

Kig på løsningen (eller `ClassIntroduction.java`) og svar på spørgsmålene:

1. Hvor mange felter har klassen? Hvor mange metoder?
2. Hvor mange lister skal du ændre, når du opretter **én** booking? Hvad sker der, hvis du glemmer én af dem?
3. Centret vil have en ny medlemstype, `"Student"`, der betaler 149 kr. og må have 3 bookinger. Hvor mange steder i koden skal du rette?
4. Hvad sker der, hvis nogen skriver `"premium"` med lille p?
5. En ny udvikler skal rette en fejl i bookingreglerne. Hvor let er det at finde det rigtige sted?
6. Hvor meget af klassen handler om menuen og input, og hvor meget handler om selve træningscenteret? Hører det sammen?
7. Hvilke **navneord** går igen i opgaveteksten? (Medlem, træningstime, …)

> Programmet virker, men det bliver hurtigt uoverskueligt. 
> Én klasse har alt for mange ansvarsområder.
> Det løser vi i trin 2.

---

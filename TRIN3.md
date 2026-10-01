# Træningscenter: fra én stor klasse til komposition og nedarving

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
- Hvad sker der, hvis du fjerner linjen `super(name, memberId);`? 
Prøv det, og læs fejlbeskeden.
- `BasicMember` har ingen `getName()`-metode. 
Hvorfor kan du alligevel kalde `sara.getName()`?



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



**Spørgsmål:** Stav med vilje forkert, fx `getMaxBooking()`, én gang **med** `@Override` og én gang **uden**. 
Hvad er forskellen? 
Hvorfor er `@Override` nyttig?



### 2.10 `abstract`

Giver det mening at skrive dette?

```java
Member member = new Member("Sara", 101);
```

Hvilken pris skal sådan et medlem betale? 
Hvor mange bookinger må det have? Der findes ikke "bare et medlem" – man er altid Basic eller Premium.

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
- Variablen `sara` har typen `Member`. 
Hvordan ved Java, at `sara.getMaxBookings()` skal returnere 2?
- `bookSession()` i `FitnessCenter` ved ikke, om medlemmet er Basic eller Premium. 
Hvorfor virker bookinggrænsen alligevel?



### 2.12 Den store test: `StudentMember`

Tilføj medlemstypen `StudentMember` (149 kr., højst 3 bookinger), og tilføj et Student-medlem til testdataene i `Main`. Prøv det i menuen.

**Spørgsmål:**
- Hvor mange filer skulle du ændre eller oprette?
- Sammenlign med dit svar på refleksionsspørgsmål 3 fra trin 1. 
Hvad er forskellen?

### 2.13 Klassediagram

I klassediagrammen: C:\Users\linah\IdeaProjects\traeningsCenter_Composition\src\diagrams\classDiagram.puml
Vis alle has-a-relationer og is-a-relationer, og marker den abstrakte klasse og de abstrakte metoder.
Sammenlign bagefter med `Klassediagram.md`.

---


## Afslutning og refleksioner: Sammenlign de to løsninger

| | Trin 1 (`ClassIntroduction`) | Trin 2 |
|---|---|---|
| Antal klasser | 1 | |
| Hvor ligger bookingreglerne? | | |
| Steder at rette for en ny medlemstype | | |
| Kan man oprette en ugyldig medlemstype? | | |
| Hvilken er nemmest at læse? | | |


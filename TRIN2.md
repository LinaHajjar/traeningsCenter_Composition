# Træningscenter: fra én stor klasse til komposition og nedarving


# Trin 2: Del programmet op

Start et nyt sæt klasser ved siden af `ClassIntroduction`. 
Behold `ClassIntroduction` uændret, så du kan sammenligne til sidst.

## Del A: Komposition (has-a)

### 2.1 Find ansvarsområderne

Tag listen med navneord fra refleksionsspørgsmål 7.

- Hvilke navneord har deres **egne data**? Det er kandidater til klasser.
- Skriv for hver kandidat: *Hvad skal klassen vide?* (felter) og *Hvad skal den kunne?* (metoder).

**Spørgsmål:** Hvilke felter i `ClassIntroduction` hører til et medlem, og hvilke hører til en træningstime?

### 2.2 Klassen `TrainingSession`

Flyt alt, der handler om én træningstime, ud i sin egen klasse.

tilføj felter + metoder (du må bruge src/diagrams/classDiagram)

**Spørgsmål:** I `ClassIntroduction` havde du fire lister om træningstimer. Hvor mange lister om træningstimer skal du bruge nu?


### 2.3 Klassen `Member`

Lav klassen `Member` med felter og metoder.
Flyt `getMaxBookings()` og `getMonthlyPrice()` over i `Member`. 
Bemærk, at de ikke længere behøver en parameter, hvorfor ikke?



### 2.4 Den første has-a: en træningstime har deltagere

Tilføj et felt til `TrainingSession`:

```java
private ArrayList<Member> participants;
```

Nu **refererer** en `TrainingSession` til instanser af en anden klasse. 
Det er komposition:

```text
TrainingSession HAS-A Member
```

Lav metoderne

```java
public boolean addParticipant(Member member) // kun hvis der er plads
public boolean removeParticipant(Member member)
```

**Spørgsmål:**
- Hvor skal listen `participants` oprettes med `new`? 
Hvad sker der, hvis du glemmer det?
- I `ClassIntroduction` talte vi deltagere med et `int`.
Hvad kan vi nu, som vi ikke kunne før?



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




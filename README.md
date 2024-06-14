# Gra Tetris

## Wprowadzenie
Projekt przedstawia implementację gry Tetris przy użyciu języka Java oraz biblioteki Swing do interfejsu użytkownika. Gra posiada podstawowe funkcje takie jak poruszanie się klocków, obracanie oraz możliwość upadku i zapełniania linii.

## Wymagania

### Wymagania funkcjonalne

1. **Rozpoczęcie gry:**
    - Gra powinna się rozpocząć po uruchomieniu aplikacji.
    - Na ekranie powinna pojawić się plansza gry z pustymi komórkami.

2. **Poruszanie klockami:**
    - Klocki powinny spadać automatycznie co określony czas.
    - Gracz powinien mieć możliwość poruszania klockami w lewo, prawo oraz ich obracania przy użyciu klawiatury:
        - Strzałka w lewo: przesunięcie klocka w lewo.
        - Strzałka w prawo: przesunięcie klocka w prawo.
        - Strzałka w górę: obrót klocka.
        - Strzałka w dół: przyspieszenie opadania klocka.

3. **Zapełnianie linii:**
    - Jeżeli pozioma linia na planszy zostanie całkowicie zapełniona klockami, powinna zostać usunięta, a gracz powinien otrzymać punkty.
    - Usunięte linie powinny spowodować przesunięcie klocków powyżej w dół.

4. **Koniec gry:**
    - Gra powinna się zakończyć, gdy nowy klocek nie może pojawić się na planszy z powodu braku miejsca.
    - Powinna pojawić się informacja o końcu gry oraz osiągnięty wynik.

### Wymagania niefunkcjonalne

1. **Wydajność:**
    - Gra powinna działać płynnie, bez zauważalnych opóźnień czy przestojów.
    - Czas reakcji na akcje gracza powinien być minimalny.

2. **Przenośność:**
    - Aplikacja powinna działać na wszystkich systemach operacyjnych, na których można uruchomić środowisko Java (JRE).

3. **Użyteczność:**
    - Interfejs użytkownika powinien być intuicyjny i łatwy w obsłudze.
    - Elementy interfejsu powinny być dobrze widoczne i czytelne.

## Instalacja i uruchomienie

1. Skompiluj projekt przy użyciu preferowanego narzędzia do budowania projektów Java (np. Maven, Gradle).
2. Uruchom aplikację przy użyciu polecenia `java -jar Tetris.jar`.

## Wersja JRE

- Aplikacja wymaga Java Runtime Environment (JRE) w wersji 8 lub nowszej.

# Uruchomienie aplikacji AWWTT

Ten przewodnik pokaże, jak uruchomić aplikację przy użyciu Dockera, od instalacji Dockera do uruchomienia aplikacji.

## Instalacja Docker (Ten krok wykonujemy włącznie kiedy nie mamy zainstalowanej aplikacji Docker desktop)

1. Pobierz **Docker Desktop** z [oficjalnej strony](https://www.docker.com/get-started/).
2. Zainstaluj pobrany program, postępując zgodnie z instrukcjami instalatora.

## Uruchomienie Aplikacji

### 1. Budowanie Obrazu Docker
1. Uruchom Docker desktop (aplikację którą przed chwilą instalowałeś/aś)
2. Otwórz terminal aby to zrobić wejdz w pasek wyszukiwania w windows, a następnie wpisz "cmd"
3. Przejdź do katalogu projektu, aby to zrobić w terminal wpisz komendę:
   ```
   cd C:\sciezka\do\glownego\katalogu\aplikacji
   ```
   ![readme-png\copy-path](readme-png\copy-path.png)
   Za ścieżkę po "cd" przekopiuj ścieżkę z katalogu gdzie zapisałeś/aś aplikację

4. Zbuduj obraz Docker swojej aplikacji:
   ```
   docker build -t awwtt .
   ```

### 2. Uruchamianie zbudowanej aplikacji
Uruchom kontener(zbudowana aplikacja) przy użyciu komendy:
```
docker run -p 8080:8080 awwtt
```
Port 8080 kontenera zostanie przekierowany na port 8080 Twojego local hosta, czyli aplikacja teraz otiera się w twojej przeglądarce pod adresem "http://localhost:8080".

### 3. Otwarcie Aplikacji
Twoja aplikacja powinna być teraz dostępna pod adresem `http://localhost:8080`.
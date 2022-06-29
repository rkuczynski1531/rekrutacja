Zrobienie aplikacji zajęło mi około 5 godzin. Zaimplementowałem wszystkie funkcjonalności oprócz security.

# Kompilacja

    mvn clean install
    
# Włączenie aplikacji na Linuxie

    ./mvnw spring-boot:run
    
# Instrukcja wykorzystywania w Postmanie

## Pacjenci
- Można tworzyć pacjentów korzystając z metody POST i wpisując url http://localhost:8080/patient i wpisując w Body pola: name, surname, age, birthday, phoneNumber, email, password.
- Usuwanie pacjentów korzystając z metody DELETE i url http://localhost:8080/patient/{id}
- Modyfikacja pacjentów korzystając z metody PUT i url http://localhost:8080/patient/{id} i w zakładce Params lub bezpośrednio w url podajemy pola które chcemy zmienić

## Zgody na udział w projekcie
- Aby dodać zgodę korzystamy z metody POST i url http://localhost:8080/consent/patient/{patientId}/project/{projectId} i w zakładce Body wybieramy opcję form-data, w key wpisujemy consent i najeżdżając kursorem na pole gdzie wpisaliśmy consent zmieniami po prawej stronie Text na File i w polu Value wybieramy plik do przesłania.

## Projekty
- Żeby dodać nowy projekt korzystamy z metody POST i url http://localhost:8080/project i w body podajemy pole title
- Usuwanie projektów korzystając z metody DELETE i url http://localhost:8080/project/{id}
- Modyfikacja projektów korzystając z metody PUT i url http://localhost:8080/project/{id} i w zakładce Params lub bezpośrednio w url podajemy pola które chcemy zmienić
- Dodawanie pacjentów do projektu - korzystamy z metody PUT i url http://localhost:8080/project/{projectId}/patient/{patientId}. Możliwe tylko po wcześniejszym dodaniu zgody

## Zlecenia na badania
- Żeby dodać zlecenie korzystamy z metody POST i url http://localhost:8080/order/patient/{patientId}/project/{projectId} i w zakładce Body podajemy pola time oraz title zlecenia. Pole time podajemy w formacie yyyy-MM-dd'T'HH:mm:ss, czyli np. 2000-10-31T01:30:00.

## Wyniki badań
 - Aby dodać wynik badań korzystamy z metody POST i url http://localhost:8080/result/order/{orderId}/patient/{patientId} i w Body wpisujemy pole title
 - Do usuwania wyników używamy metody DELETE i url http://localhost:8080/result/{resultId}
 - Do modyfikacji wyników używamy metody PUT i url http://localhost:8080/result/{resultId} i w zakładce Params lub bezpośrednio w url podajemy pola, które chcemy zmienić, mogą być to title lub patientId

package ecole221.exo_stream;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public record Person(String matricule, String nom, LocalDate dateNaissance) {

    public int getAge() {
        return Period.between(this.dateNaissance, LocalDate.now()).getYears();
    }

    public static void ligneSeparatrice() {
        System.out.println("-".repeat(50));
    }

    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person("001", "Tapha", LocalDate.parse("1970-09-25")),
                new Person("002", "Moussa", LocalDate.parse("1950-09-25")),
                new Person("003", "Fatou", LocalDate.parse("2003-09-25")),
                new Person("004", "Saly", LocalDate.parse("2002-09-25")),
                new Person("005", "Moussa", LocalDate.parse("2007-09-25")),
                new Person("006", "Badara", LocalDate.parse("2010-09-25")),
                new Person("006", "Abdou", LocalDate.parse("1985-01-02"))
        );

        // Liste des employés ayant plus de 18 ans
        List<Person> isOld18 = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .toList();

        System.out.println("Personnes ayant plus de 18 ans :");
        isOld18.forEach(System.out::println);

        ligneSeparatrice();

        // Liste des employés ayant plus de 60 ans
        List<Person> isOld60 = persons.stream()
                .filter(person -> person.getAge() > 60)
                .toList();
        System.out.println("Personnes ayant plus de 60 ans :");
        isOld60.forEach(System.out::println);

        ligneSeparatrice();

        System.out.println("Tri par ordre croissant selon l'age :");
        List<Person> filterByAsc = persons.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .toList();
        filterByAsc.forEach(System.out::println);

        ligneSeparatrice();

        System.out.println("Tri par ordre alphabetique :");
        List<Person> filterByName = persons.stream()
                .sorted(Comparator.comparing(Person::nom))
                .toList();
        filterByName.forEach(System.out::println);

        ligneSeparatrice();

        System.out.println("Liste des noms :");
        List<String> namesList = persons.stream()
                .map(person -> person.nom)
                .toList();
        namesList.forEach(System.out::println);

        ligneSeparatrice();

        // Calcul de l'age moyen
        Double moyenne_ages = persons.stream()
                .mapToInt(Person::getAge)
                .average().orElse(0.0);
        System.out.println("Age moyenne des employés : " + moyenne_ages.intValue());

        ligneSeparatrice();

        System.out.println("L'employé le plus jeune :");
        Optional<Person> lessOld = persons.stream()
                .min(Comparator.comparing(Person::getAge));
        lessOld.ifPresent(System.out::println);

        ligneSeparatrice();

        System.out.println("L'employé le plus âgé :");
        Optional<Person> moreOld = persons.stream()
                .max(Comparator.comparing(Person::getAge));
        moreOld.ifPresent(System.out::println);

        ligneSeparatrice();

        Map<Boolean, List<Person>> employes = persons.stream()
                .collect(Collectors.partitioningBy(isLess40 -> isLess40.getAge() < 40));
        // Liste des employés ayant moins de 40 ans
        System.out.println("Personnes ayant moins de 40 ans :" + employes.get(true));
        // Liste des employés ayant 40 ans et plus
        System.out.println("Personnes ayant 40 ans et plus :" + employes.get(false));

        ligneSeparatrice();

        // Vérifier s'il existe au moins un employé ayant moins de 18 ans.
        boolean hasMinor = persons.stream()
                .anyMatch(person -> person.getAge() < 18);
        System.out.println("Existe-t-il un employé de moins de 18 ans ? " + hasMinor);

        ligneSeparatrice();

        // Vérifier si tous les employés ont un matricule non nul.
        boolean allHaveMatricule = persons.stream()
                .allMatch(person -> person.matricule() != null && !person.matricule().isEmpty());
        System.out.println("Tous les employés ont-ils un matricule non nul ? " + allHaveMatricule);

        ligneSeparatrice();

        // Pour chaque employé, calculer la date exacte à laquelle il atteindra 60 ans.
        System.out.println("Date à laquelle chaque employé atteindra 60 ans :");
        persons.forEach(person -> {
            LocalDate dateAt60 = person.dateNaissance().plusYears(60);
            System.out.println(person.nom() + " atteindra 60 ans le : " + dateAt60);
        });

        ligneSeparatrice();

        // Récupérer tous les employés nés avant l’année 1990.
        List<Person> bornBefore1990 = persons.stream()
                .filter(person -> person.dateNaissance().getYear() < 1990)
                .toList();
        System.out.println("Employés nés avant 1990 :");
        bornBefore1990.forEach(System.out::println);

        ligneSeparatrice();

        // Récupérer tous les employés nés après l’année 2000.
        List<Person> bornAfter2000 = persons.stream()
                .filter(person -> person.dateNaissance().getYear() > 2000)
                .toList();
        System.out.println("Employés nés après 2000 :");
        bornAfter2000.forEach(System.out::println);

        ligneSeparatrice();

        // Récupérer tous les employés nés durant les années 1980 (entre 1980 et 1989 inclus).
        List<Person> bornIn1980s = persons.stream()
                .filter(person -> person.dateNaissance().getYear() >= 1980 && person.dateNaissance().getYear() <= 1989)
                .toList();
        System.out.println("Employés nés dans les années 1980 :");
        bornIn1980s.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return String.format("Matricule: %s, Nom: %s, Age: %d;", matricule, nom, getAge());
    }
}
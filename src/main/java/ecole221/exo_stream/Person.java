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

    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person ("001", "Tapha", LocalDate.parse("1970-09-25")),
                new Person("002", "Moussa",  LocalDate.parse("1950-09-25")),
                new Person("003", "Fatou",  LocalDate.parse("2003-09-25")),
                new Person("004", "Saly",  LocalDate.parse("2002-09-25")),
                new Person("005", "Moussa",  LocalDate.parse("2007-09-25")),
                new Person("006", "Badara",  LocalDate.parse("2010-09-25")),
                new Person("006", "Abdou",  LocalDate.parse("1985-01-02"))
        );

        // Liste des employés ayant plus de 18 ans
        List<Person> isOld18 = persons.stream()
                .filter(person -> person.getAge() >= 18 )
                .toList();

        System.out.println("Personnes ayant plus de 18 ans :");
        isOld18.forEach(System.out::println);

        System.out.println("-".repeat(50));

        // Liste des employés ayant plus de 60 ans
        List<Person> isOld60 = persons.stream()
                .filter(person -> person.getAge() > 60)
                .toList();
        System.out.println("Personnes ayant plus de 60 ans :");
        isOld60.forEach(System.out::println);

        System.out.println("-".repeat(50));

        System.out.println("Tri par ordre croissant selon l'age :");
        List<Person> filterByAsc = persons.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
//               .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .toList();
        filterByAsc.forEach(System.out::println);

        System.out.println("-".repeat(50));

        System.out.println("Tri par ordre alphabetique :");
        List<Person> filterByName = persons.stream()
                .sorted(Comparator.comparing(Person::nom))
//               .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .toList();
        filterByName.forEach(System.out::println);

        System.out.println("-".repeat(50));

        System.out.println("Liste des noms :");
        List<String> namesList = persons.stream()
                .map(person -> person.nom)
                .toList();
        namesList.forEach(System.out::println);

        System.out.println("-".repeat(50));

        // Calcul de l'age moyenne
        Double moyenne_ages = persons.stream()
                .mapToInt(Person::getAge)
                .average().orElse(0.0);
        System.out.println("Age moyenne des employés : " + moyenne_ages.intValue());

        System.out.println("-".repeat(50));

        System.out.println("L'employé le plus jeune :");
        Optional<Person> lessOld = persons.stream()
                .min(Comparator.comparing(Person::getAge));
        lessOld.ifPresent(System.out::println);

        System.out.println("-".repeat(50));

        System.out.println("L'employé le plus âgé :");
        Optional<Person> moreOld = persons.stream()
                .max(Comparator.comparing(Person::getAge));
        moreOld.ifPresent(System.out::println);

        System.out.println("-".repeat(50));

        Map<Boolean, List<Person>> employes = persons.stream()
                .collect(Collectors.partitioningBy(isLess40 -> isLess40.getAge() < 40));
        // Liste des employés ayant moins de 40 ans
        System.out.println("Personnes ayant moins de 40 ans :" + employes.get(true));
        // Liste des employés ayant 40 ans et plus
        System.out.println("Personnes ayant 40 ans et plus :" + employes.get(false));

        System.out.println("-".repeat(50));

        // Vérifie s'il existe au moins un employé ayant moins de 18 ans.


        // Vérifie si tous les employés ont un matricule non nul.


        // Pour chaque employé, calcule la date exacte à laquelle il atteindra 60 ans, en ajoutant 60 années à sa dateNaissance.


        // Calculez l’âge réel (aujourd’hui)


        // Récupère tous les employés :
        //    • Nés avant l’année 1990.

        //    • Nés après l’année 2000.

        //    • Nés durant les années 1980 (entre 1980 et 1989 inclus)

    }


    @Override
    public String toString() {
        return String.format("Matricule: %s, Nom: %s, Age: %d;", matricule, nom, getAge());
    }
}

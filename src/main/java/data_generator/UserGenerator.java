package data_generator;

import com.github.javafaker.Faker;
import model.User;

import java.util.Locale;
import java.util.Random;

// Класс для генерации данных пользователя
public class UserGenerator {

    private static Faker fakerEn = new Faker();
    private static Faker fakerRu = new Faker(Locale.forLanguageTag("ru"));

    // Метод возвращает уникальный email
    public static String getUniqueEmail() {
        Random random = new Random();
        String email = fakerRu.internet().emailAddress(fakerEn.name().username() + random.nextInt(1000));
        return email;
    }

    // Метод возвращает пользователя с уникальными данными.
    // Параметры: минимальная длина пароля (включительно), максимальная длина пароля (не включительно).
    public static User getUniqueUser(int minLengthPswd, int maxLengthPswdNotInclsv) {

        String name = fakerRu.name().name();
        String password = fakerEn.internet()
                .password(minLengthPswd,maxLengthPswdNotInclsv, true, false, true);

        return new User(name, getUniqueEmail(), password);
    }



}

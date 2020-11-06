package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojouser.UserCreate;
import com.github.javafaker.Faker;

import java.util.Locale;

public class Data {
    private static final String NUMBER_PATTERN = "2231";
    private static final String LETTER_STRING_SIZE_5 = "?????";

    private static final String MX = "es-MX";

    private static Logger logger = LoggerFactory.getLogger(Data.class);

    private static Faker fakerMx = new Faker(new Locale(MX));
    JsonObject userData;

    public void setProperty(String key, Object value) {
        Gson gson = new Gson();
        this.userData.add(key, gson.toJsonTree(value));
    }

    public static UserCreate generateBasicDataWithEmailAndPass(String email, String pass){

        return UserCreate.builder()
                .email(email)
                .password(pass)
                .first_name(fakerMx.letterify(LETTER_STRING_SIZE_5))
                .last_name(fakerMx.letterify(LETTER_STRING_SIZE_5))
                .id(fakerMx.bothify(NUMBER_PATTERN))
                .ciudad(fakerMx.country().capital())
                .pais(fakerMx.country().name())
                .build();
    }

}

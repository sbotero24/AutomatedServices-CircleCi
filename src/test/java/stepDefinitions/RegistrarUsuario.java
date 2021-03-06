package stepDefinitions;

import com.google.gson.Gson;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.annotations.Steps;
import pojouser.UserCreate;
import question.ResponseCode;
import tasks.RegisterUser;
import util.Data;

import static io.restassured.path.json.JsonPath.from;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;
import static util.GenericConstants.NEW_PARTIAL_USER_DATA;

public class RegistrarUsuario {
    public String var;
    private static final String URL_BASE = "https://reqres.in";
    Actor actor;

    @Steps(shared = true)
    private Data data;

    @Dado("^que (.*) es nuevo usuario y quiere registrarse$")
    public void queSantiagoEsNuevoUsuarioYQuiereRegistrarse(String usr) {
        actor = Actor.named(usr)
                .whoCan(CallAnApi.at(URL_BASE));
    }


    @Cuando("^se envie la solicitud de registro en la app con (.*) y (.*)$")
    public void seEnvieLaSolicitudDeRegistroEnLaApp(String email, String pass) {
        UserCreate userCreate = new UserCreate();
        userCreate.setEmail(email);
        userCreate.setPassword(pass);


        actor.attemptsTo(
                RegisterUser.withInfo(userCreate)
        );
////    String body = actor.recall("respuesta").toString();
////        System.out.println(body);
//        UserCreate user = Data.generateBasicDataWithEmailAndPass(email,pass);
//        data.setProperty(NEW_PARTIAL_USER_DATA.toString(), user);
//        System.out.println(user.getPais());
//        actor.remember(NEW_PARTIAL_USER_DATA.toString(), user);
//        System.out.println(data);


    }

    private static String LOGE = "loge";

    @Entonces("^el deberia ver que el registro fue exitoso con el status (.*)$")
    public void elDeberiaVerQueElRegistroFueExitoso(int sc) {
        actor.remember(LOGE, actor1 -> SerenityRest.lastResponse().body().asString());
        String var = actor.recall(LOGE);
        System.out.println(var);
        int id = from(var).get("id");
        System.out.println(id);
        String token = from(var).get("token");
        System.out.println(token);
        actor.should(
                seeThat("el codigo de creacion de usuario es valido", ResponseCode.was(), equalTo(sc)));
//        actor.should(
//                seeThat("el id es igual", from(var).get("id"),equalTo(4)));

    }

}
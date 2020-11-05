package stepDefinitions;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import pojouser.UserCreate;
import question.ResponseCode;
import tasks.RegisterUser;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class RegistrarUsuario {

    private static final String URL_BASE = "https://reqres.in";
    Actor actor;

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
    }

    @Entonces("^el deberia ver que el registro fue exitoso con el status (.*)$")
    public void elDeberiaVerQueElRegistroFueExitoso(int sc) {
        actor.should(
                seeThat("el codigo de creacion de usuario es valido", ResponseCode.was(), equalTo(sc)));
    }

}
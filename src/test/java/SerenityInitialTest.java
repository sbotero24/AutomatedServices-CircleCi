import models.users.Datum;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import pojouser.UserCreate;
import question.ResponseCode;
import tasks.GetJsonResponse;
import tasks.GetUsers;
import tasks.RegisterUser;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SerenityRunner.class)
public class SerenityInitialTest {

    private static final String URL_BASE = "https://reqres.in";

    @Test
    public void getUsers() {

        Actor santiago = Actor.named("Santiago TAE")
                .whoCan(CallAnApi.at(URL_BASE));

        santiago.attemptsTo(
                GetUsers.from(2)
        );


        santiago.should(
                seeThat("el codigo de respuesta", ResponseCode.was(), equalTo(200))
        );

        Datum users = new GetJsonResponse().answeredBy(santiago)
                .getData().stream()
                .filter(x -> x.getId() == 9).findFirst().orElse(null);

        santiago.should(
                seeThat("usuario no es nulo", actor -> users, notNullValue())
        );
        santiago.should(
                seeThat("el email del usuario ",
                        actor -> users.getEmail(), equalTo("tobias.funke@reqres.in"))
        );
    }

    @Test
    public void registerUserTest() {

        Actor santiago = Actor.named("Santiago TAE")
                .whoCan(CallAnApi.at(URL_BASE));
        String registerUserInfo = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        santiago.attemptsTo(
                RegisterUser.withInfo(registerUserInfo)
        );

        santiago.should(
                seeThat("el codigo de creacion de usuario es valido", ResponseCode.was(),equalTo(201)));

    }

    @Test
    public void registerUserWithModelsTest() {

        Actor santiago = Actor.named("Santiago TAE")
                .whoCan(CallAnApi.at(URL_BASE));

        UserCreate userCreate = new UserCreate();
        userCreate.setEmail("eve.holt@reqres.in");
        userCreate.setPassword("pistol");


        santiago.attemptsTo(
                RegisterUser.withInfo(userCreate)
        );
        /*String registerUserInfo = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";*/

//        santiago.attemptsTo(
//                RegisterUser.withInfo(registerUserInfo)
//        );

        santiago.should(
                seeThat("el codigo de creacion de usuario es valido", ResponseCode.was(),equalTo(201)));

    }

}

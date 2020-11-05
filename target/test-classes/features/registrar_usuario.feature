#language: es
@microservicios:pruebasApi
Característica: yo como automatizador requiero que validar que el servicio
  para registrar usuarios se comporte de la manera esperada.

  @registro
  Esquema del escenario: Registro exitoso de clientes
    Dado que <user> es nuevo usuario y quiere registrarse
    Cuando se envie la solicitud de registro en la app con <email> y <pass>
    Entonces el deberia ver que el registro fue exitoso con el status <sc>

    Ejemplos:

      | user     | email              | pass   | sc  |
      | Santiago | eve.holt@reqres.in | pistol | 201 |


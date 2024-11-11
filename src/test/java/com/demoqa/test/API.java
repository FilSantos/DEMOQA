package com.demoqa.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;

import com.demoqa.test.hooks.CucumberHooks;

import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class API {

    private String baseUrl = "https://demoqa.com";
    private String userId;
    private String username = "user" + new Random().nextInt(1000);
    private String password = "Password@123";
    private String token;
    private String book1;
    private String book2;
    private Scenario scenario = CucumberHooks.getScenario();
    
    
    @Dado("que crio um novo usuário")
    public void crioNovoUsuario() {
        RestAssured.baseURI = baseUrl;

        Response response = given()
            .header("Content-Type", "application/json")
            .body("{ \"userName\": \"" + username + "\", \"password\": \"" + password + "\" }")
            .when()
            .post("/Account/v1/User")
            .then()
            .log().body()
            .statusCode(201)
            .extract().response();
        scenario.attach(response.getBody().asString(), "application/json", "Response");
        userId = response.jsonPath().getString("userID");
        Assert.assertNotNull("Usuário não foi criado!", userId);
    }

    @Quando("gero um token de acesso")
    public void geroTokenAcesso() {
        Response response = given()
            .header("Content-Type", "application/json")
            .body("{ \"userName\": \"" + username + "\", \"password\": \"" + password + "\" }")
            .when()
            .post("/Account/v1/GenerateToken")
            .then()
            .log().body()
            .statusCode(200)
            .extract().response();
        scenario.attach(response.getBody().asString(), "application/json", "Response");
        token = response.jsonPath().getString("token");
        Assert.assertNotNull("Token não foi gerado!", token);
    }

    @Então("verifico se o usuário está autorizado")
    public void verificoUsuarioAutorizado() {
    	Response response = given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + token)
            .body("{ \"userName\": \"" + username + "\", \"password\": \"" + password + "\" }")
            .when()
            .post("/Account/v1/Authorized")
            .then()
            .log().body()
            .statusCode(200)
            .extract().response();
    	scenario.attach(response.getBody().asString(), "application/json", "Response");
    }

    @Quando("listo os livros disponíveis")
    public void listaLivrosDisponiveis() {
        Response response = given()
            .header("Authorization", "Bearer " + token)
            .when()
            .get("/BookStore/v1/Books")
            .then()
            .log().body()
            .statusCode(200)
            .extract().response();
        scenario.attach(response.getBody().asString(), "application/json", "Response");
        List<Map<String, String>> books = response.jsonPath().getList("books");
        Assert.assertTrue("Nenhum livro disponível!", books.size() > 1);

        book1 = books.get(new Random().nextInt(books.size())).get("isbn");
        book2 = books.get(new Random().nextInt(books.size())).get("isbn");
    }

    @Então("alugo dois livros aleatórios")
    public void alugoDoisLivrosAleatorios() {
    	ValidatableResponse response = given()
            .header("Authorization", "Bearer " + token)
            .header("Content-Type", "application/json")
            .body("{ \"userId\": \"" + userId + "\", \"collectionOfIsbns\": [ { \"isbn\": \"" + book1 + "\" }, { \"isbn\": \"" + book2 + "\" } ] }")
            .when()
            .post("/BookStore/v1/Books")
            .then()
            .log().body()
            .statusCode(201)
            .body("books.isbn", hasItems(book1, book2));
        scenario.attach(response.log().body().extract().asString(), "application/json", "Response");
    }

    @Então("verifico os detalhes do usuário com os livros alugados")
    public void verificoDetalhesUsuarioLivrosAlugados() {
    	ValidatableResponse response = given()
            .header("Authorization", "Bearer " + token)
            .when()
            .get("/Account/v1/User/" + userId)
            .then()
            .log().body()
            .statusCode(200)
            .body("books.isbn", hasItems(book1, book2));
    	scenario.attach(response.log().body().extract().asString(), "application/json", "Response");
    }
	
}

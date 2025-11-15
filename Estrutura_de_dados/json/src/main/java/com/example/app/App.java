package com.example.app;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.nio.util.SharedOutputBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

public class App {

    public static void main(String[] args) throws UnirestException {
        System.out.println("| ============= Exemplo 1 ============= |");
        String url =
            "https://fmsampaio.github.io/helper-sites/json-examples/exemplo-1.json";

        HttpResponse<JsonNode> response = Unirest.get(url)
            .header("Content-Type", "application/json")
            .asJson();

        int code = response.getStatus();
        JsonNode json = response.getBody();
        System.out.println("Response code: " + code);

        JSONObject obj = json.getObject();
        //JSONArray array = json.getArray();
        String nome = obj.getString("employee_name");
        Integer idade = obj.getInt("age");
        String cidade = obj.getString("city");

        System.out.println("Name: " + nome);
        System.out.println("Age: " + idade);
        System.out.println("city: " + cidade);

        System.out.println("| ============= Exemplo 2 ============= |");
        String url2 =
            "https://fmsampaio.github.io/helper-sites/json-examples/exemplo-2.json";

        HttpResponse<JsonNode> response2 = Unirest.get(url2)
            .header("Content-Type", "application/json")
            .asJson();

        int code2 = response2.getStatus();
        JsonNode json2 = response2.getBody();
        System.out.println("Response code: " + code2);

        JSONObject obj2 = json2.getObject();
        JSONArray array_empregados = obj2.getJSONArray("employees");
        System.out.println(array_empregados);

        array_empregados.forEach(empregado -> System.out.println(empregado));

        System.out.println("| ============= Caminho contrário ============= |");

        String url3 = "https://ed-json-post-23762f735f6e.herokuapp.com/data";

        JSONObject obj3 = new JSONObject();
        obj3.put("nome", "Luciano Magri");
        obj3.put("nasc", "12/11/1985");
        obj3.put("email", "luciano.magri@aluno.farroupilha.ifrs.edu.br");
        obj3.put("reside_farroupilha", true);
        obj3.put("altura", 1.70);

        JSONObject curso = new JSONObject();
        curso.put("ano_ingresso", 2022);
        curso.put("inst", "IFRS Campus Farroupilha");
        curso.put("nome", "Análise e Desenvolvimento de Sistemas");

        JSONArray disciplina = new JSONArray();
        disciplina.put("Estruturas de dados");
        disciplina.put("Projeto e desenvolvimento de interfaces WEB");
        disciplina.put("Qualidade e teste de software");

        curso.put("disciplinas", disciplina);

        obj3.put("curso", curso);

        //HttpResponse<JsonNode> response3 = Unirest.post(url3)
        //    .header("Content-Type", "application/json")
        //    .body(obj3) //Objeto JSON com os dados a serem enviados à API
        //    .asJson();

        //System.out.println("Response code: " + response3.getStatus());
        //
        JSONArray j = new JSONArray(List.of("a", "b"));
        System.out.println(j);
    }
}

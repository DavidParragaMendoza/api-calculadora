package br.edu.ifrn.calculadora.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraController {

    @GetMapping("/calculadora/somar/{numero1}/{numero2}")
    public double somar(@PathVariable double numero1, @PathVariable double numero2) {
        return numero1 + numero2;
    }

    @GetMapping("/calculadora/subtrair")
    public double subtrair(@RequestParam double numero1, @RequestParam double numero2) {
        return numero1 - numero2;
    }

    @GetMapping(value = "/calculadora/calcular/{operacao}", produces = "text/plain;charset=UTF-8")
    public String calcular(
            @PathVariable String operacao,
            @RequestParam double numero1,
            @RequestParam double numero2,
            @RequestParam(defaultValue = "2") int casasDecimais) {

        double resultado;

        switch (operacao) {
            case "somar"-> resultado = numero1 + numero2;
            case "subtrair" -> resultado = numero1 - numero2;
            case "multiplicar" -> resultado = numero1 * numero2;
            case "dividir" -> {
                if (numero2 == 0) {
                    return "Erro: não é possível dividir por zero";
                }
                resultado = numero1 / numero2;
            }
            default->{
                return "Erro: operação inválida";
            }
        }

        String formato = "%." + casasDecimais + "f";
        return "Operação: " + operacao + "\n"
             + "Número 1: " + numero1 + "\n"
             + "Número 2: " + numero2 + "\n"
             + "Resultado: " + String.format(formato, resultado);
    }

    @GetMapping(value = "/calculadora/par-ou-impar/{numero}", produces = "text/plain;charset=UTF-8")
    public String parOuImpar(@PathVariable int numero) {
        if (numero % 2 == 0) {
            return numero + " é PAR";
        } else {
            return numero + " é ÍMPAR";
        }
    }

    @GetMapping(value = "/calculadora/analisar/{numero}", produces = "text/plain;charset=UTF-8")
    public String analisar(@PathVariable double numero) {
        String parOuImpar = (numero % 2 == 0) ? "PAR" : "ÍMPAR";

        String sinal;
        if (numero > 0) {
            sinal = "POSITIVO";
        } else if (numero < 0) {
            sinal = "NEGATIVO";
        } else {
            sinal = "ZERO";
        }

        return "Número: " + numero + "\n"
             + "Par ou impar: " + parOuImpar + "\n"
             + "Positivo, negativo ou zero: " + sinal + "\n"
             + "Dobro: " + (numero * 2) + "\n"
             + "Metade: " + (numero / 2) + "\n"
             + "Quadrado: " + (numero * numero);
    }

    @GetMapping(value = "/calculadora/media", produces = "text/plain;charset=UTF-8")
    public String media(
            @RequestParam double nota1,
            @RequestParam double nota2,
            @RequestParam double nota3) {

        double media = (nota1 + nota2 + nota3) / 3;

        String situacao;
        if (media >= 7) {
            situacao = "APROVADO";
        } else if (media >= 4) {
            situacao = "RECUPERAÇÃO";
        } else {
            situacao = "REPROVADO";
        }

        return "Média: " + String.format("%.2f", media) + "\n"
             + "Situação: " + situacao;
    }

}

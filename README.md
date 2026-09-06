# Guía de Ejecución y Pruebas - API Calculadora Spring Boot

## 1. Cómo ejecutar el proyecto
Dado que estás usando IntelliJ IDEA, tienes dos formas sencillas de levantar el servidor:

*   **Opción A (Visual):** Abre la clase `ApiCalculadoraApplication.java` (la que tiene el método `main`) y haz clic en el botón de "Play" (triángulo verde) que aparece en el margen izquierdo junto a la declaración de la clase.
*   **Opción B (Terminal):** Abre la terminal integrada de IntelliJ (abajo) y ejecuta el comando: 
    ```bash
    mvn spring-boot:run
    ```

Espera unos segundos hasta que veas un mensaje en la consola similar a `Tomcat started on port 8080 (http)`. Esto significa que la API ya está escuchando peticiones.

---

## 2. Cómo probar los endpoints
Como todos los métodos utilizan peticiones `GET`, la forma más rápida de probarlos es simplemente copiando las siguientes URLs y pegándolas directamente en la barra de direcciones de tu navegador web. También puedes usar herramientas como Postman, Thunder Client o `curl`.

### 1. Endpoint de Soma (@PathVariable)
*   **URL a probar:** [http://localhost:8080/calculadora/somar/10/5](http://localhost:8080/calculadora/somar/10/5)
*   **Resultado esperado:** `15.0`

### 2. Endpoint de Subtração (@RequestParam)
*   **URL a probar:** [http://localhost:8080/calculadora/subtrair?numero1=20&numero2=8](http://localhost:8080/calculadora/subtrair?numero1=20&numero2=8)
*   **Resultado esperado:** `12.0`

### 3. Endpoint único de Cálculo (@PathVariable y @RequestParam)
Prueba estas tres variantes para asegurar que el `switch` y los parámetros opcionales funcionan:

*   **Multiplicación básica:** [http://localhost:8080/calculadora/calcular/multiplicar?numero1=10&numero2=5](http://localhost:8080/calculadora/calcular/multiplicar?numero1=10&numero2=5)
*   **Suma con 3 decimales (parámetro opcional):** [http://localhost:8080/calculadora/calcular/somar?numero1=10&numero2=3.5&casasDecimais=3](http://localhost:8080/calculadora/calcular/somar?numero1=10&numero2=3.5&casasDecimais=3)
*   **Validación de División por cero:** [http://localhost:8080/calculadora/calcular/dividir?numero1=10&numero2=0](http://localhost:8080/calculadora/calcular/dividir?numero1=10&numero2=0)
    *(El navegador debe mostrar exactamente: `Erro: não é possível dividir por zero`)*

### 4. Par ou Ímpar
*   **URL a probar:** [http://localhost:8080/calculadora/par-ou-impar/8](http://localhost:8080/calculadora/par-ou-impar/8)
*   **Resultado esperado:** Debería indicar que es `PAR`.

### 5. Análise de Número
*   **URL a probar:** [http://localhost:8080/calculadora/analisar/10](http://localhost:8080/calculadora/analisar/10)
*   **Resultado esperado:** Verás el texto estructurado con el Número, Par/Ímpar, Positivo/Negativo/Zero, Dobro, Metade y Quadrado. *(Nota: El navegador a veces quita los saltos de línea visuales, pero si haces clic derecho > "Ver código fuente de la página", verás los saltos de línea correctamente).*

### 6. Desafío adicional: Cálculo de Média
Prueba los tres escenarios lógicos:

*   **Aprobado (>= 7):** [http://localhost:8080/calculadora/media?nota1=7&nota2=8&nota3=6](http://localhost:8080/calculadora/media?nota1=7&nota2=8&nota3=6)
*   **Recuperación (>= 4 y < 7):** [http://localhost:8080/calculadora/media?nota1=5&nota2=4&nota3=6](http://localhost:8080/calculadora/media?nota1=5&nota2=4&nota3=6)
*   **Reprobado (< 4):** [http://localhost:8080/calculadora/media?nota1=2&nota2=3&nota3=1](http://localhost:8080/calculadora/media?nota1=2&nota2=3&nota3=1)

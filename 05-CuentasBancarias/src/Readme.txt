¿Qué tipo de dato se utilizó para el saldo? ¿Es el más apropiado?

Se utilizó double. Es común para representar dinero, pero no es el más apropiado debido a problemas de precisión con decimales.
Lo ideal sería usar BigDecimal en Java para operaciones financieras.


¿Qué visibilidad tiene el atributo saldo de la Cuenta? ¿Es correcto?

Tiene visibilidad public, lo cual no es correcto. Permite modificar el saldo desde cualquier parte del programa, lo que viola el principio de encapsulamiento.


Si extraigo más dinero del disponible en saldo, seguramente pueda hacerlo. ¿Está bien que así sea?

No, no está bien. El diseño actual no impide extracciones que dejen el saldo negativo, lo cual no es deseable en una cuenta bancaria común.
Esto se corregirá en la Parte 2, aplicando encapsulamiento.
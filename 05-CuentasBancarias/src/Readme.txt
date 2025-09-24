**Respuestas a las preguntas de la Parte 3**

¿Qué parámetros recibe el método transferir?
- Recibe dos parámetros:
	♦ monto: el dinero a transferir.
	♦ cuentaDestino: la cuenta a la que se transfiere el dinero.
	
	
¿Qué validaciones se hacen antes de transferir?
- Se verifica que:
	♦ El monto sea positivo.
	♦ Haya saldo suficiente en la cuenta origen.
	
	
¿Qué métodos se reutilizan dentro de transferir?
- Se reutilizan los métodos:
	♦ método debitar(double monto), que además verifica que haya fondos suficientes.
	♦ acreditar(monto) para sumar el dinero a la cuenta destino.
	
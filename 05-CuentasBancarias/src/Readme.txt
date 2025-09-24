¿Qué visibilidad tiene el atributo saldo ahora? ¿Es correcto?

- Ahora es private, lo cual es correcto. Esto impide el acceso directo desde fuera de la clase y permite controlar cómo se modifica el saldo.


¿Cómo se accede al saldo desde fuera de la clase?

- A través del método público consultarSaldo(), que devuelve una copia del valor del saldo.


¿Cómo se acredita dinero a la cuenta? ¿Y cómo se debita?

- Se acredita con el método acreditar(double monto).
- Se debita con el método debitar(double monto), que además verifica que haya fondos suficientes.
TIGHTPOKER - Gestión de Torneos de Casino
==========================================

Autores del proyecto:
- Virgilio Jesús Domínguez González
- Jose Antonio Bernaza
- Sergio Ponce Castro

Proyecto desarrollado para las asignaturas de:
- Entorno de desarrollo
- Programación
- Base de datos

------------------------------------------------------------
Descripción General
------------------------------------------------------------

TightPoker es una aplicación orientada a la gestión de torneos en casinos de toda España. 
Los torneos están clasificados por tipo de juego (KO Progresivo, MonsterStack, Mistery Bounty), 
y los usuarios pueden registrarse, iniciar sesión e inscribirse en ellos.

La interfaz también muestra los torneos más cercanos según la ubicación del usuario.

------------------------------------------------------------
Características Principales
------------------------------------------------------------

- Registro y Login de usuarios  
  Permite a los usuarios registrarse, iniciar sesión y gestionar su cuenta.

- Interfaz amigable  
  Diseño sencillo e intuitivo para facilitar su uso.

- Gestión de torneos  
  Los torneos se gestionan desde una base de datos SQL. Incluye control de inscripciones  
  (se cierra al llegar al límite de jugadores).

- Gestión de casinos  
  Muestra los casinos más cercanos al usuario según su código postal.

- Gestión de saldo  
  Los usuarios pueden añadir o retirar saldo virtual mediante un número de tarjeta  
  (sin conexión bancaria real).

- Edición de datos del usuario  
  Posibilidad de modificar información personal en cualquier momento.

------------------------------------------------------------
Estructura de la Base de Datos (SQL)
------------------------------------------------------------

1. Tabla: USUARIOS
   - dni              (PK)  → Identificador único  
   - nombre           → Nombre del usuario  
   - apellidos        → Apellidos del usuario  
   - nombreUsuario    → Alias único para el login  
   - contraseña       → Contraseña de acceso  
   - edad             → Verificación de mayoría de edad  
   - teléfono         → No es único (puede repetirse)  
   - codigoPostal     → Se utiliza para ubicar torneos cercanos  
   - n_tarjeta        → Número de tarjeta para saldo virtual  
   - saldo            → Moneda virtual del usuario  

2. Tabla: TORNEOS
   - id               (PK)  → Identificador del torneo  
   - nombre           → Nombre del torneo  
   - lugar            → Ubicación del evento  
   - buy_in           → Precio de inscripción (resta al saldo)  
   - jugadores        → Jugadores actualmente inscritos  
   - fecha            → Fecha del torneo (no usada aún)  
   - bote_premios     → Recompensa del torneo (sin uso actual)  
   - limite_jugadores → Máximo de jugadores permitidos  
   - tipo             → Tipo de torneo (KO, MonsterStack, etc.)  

3. Tabla: CASINOS
   - nombre           → Nombre del casino  
   - lugar            → Dirección  
   - n_mesas          → Número de mesas disponibles  

4. Tabla: INSCRIPCIONES
   - nombreUsuario    → Usuario inscrito  
   - id               → ID del torneo al que se ha inscrito  

------------------------------------------------------------
Notas Finales
------------------------------------------------------------

- El programa bloquea el acceso a menores de edad.  
- Las operaciones de saldo son ficticias, no hay integración bancaria real.  
- Se planean futuras mejoras como el uso del campo "fecha" y "bote_premios".

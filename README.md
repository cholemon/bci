# ITUNES SEARCH - ANDROID

### Desarrollo de App Android.

#### [-By Levi A. Hurtado-]
+ La app puede trabajar tanto de forma Online como Offline. Para ello, se almacenan localmente la data previamente consultada (album - tracks).

+ El proyecto fué desarrollado en Java como lenguaje nativo para android. Se implementa el patrón de diseño MVC para una clara estructura y comunicación de componentes; esto nos permite mantener un desarrollo claro y escalable en el tiempo.

+ Se usó Sqlite para guardar localmente la data, y así, hacer consultas posteriormente (cuando el estado de la señal esté OFFLINE).

+ Para el consumo de la API Rest, se implementó el uso de la librería OKHTTP (ligera y fácil de implementar).

+ Así mismo, se implementó la librería Picasso para hacer el render de las imágenes y caché local de las mismas.

+ Para el preview del track, se hace uso del Intent de Android para seleccionar el reproductor del sistema.

(Se debe mejorar la forma como se obtiene el estado de la conexión a internet, para no bloquear el hilo principal de la app)



# Algunos screenshot de la app
 Vistas                    |  Vistas
:-------------------------:|:-------------------------:
![](https://downstagram.com/images/1.jpg)  |  ![](https://downstagram.com/images/2.jpg)
![](https://downstagram.com/images/3.jpg)  |  ![](https://downstagram.com/images/4.jpg)

# Demo
[Ver en youtube](https://www.youtube.com/watch?v=dGUy7qzDVFA)

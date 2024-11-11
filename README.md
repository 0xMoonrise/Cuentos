# Cuentos

Una aplicación para dispositivos Android diseñada para ofrecer una experiencia visual de cuentos. La app permite ver videos de cuentos clásicos, o cualquier tipo de video, con una interfaz intuitiva y efectos de transición agradables.

## Descripción
Cuentos es una aplicación simple, compacta y fácil de mantener, desarrollada para visualizar videos de cuentos infantiles. Con una interfaz amigable y animaciones que simulan un telón bajando, ofrece una experiencia inmersiva ideal para los más pequeños.

## Características
- **Lista de cuentos:**
  - La liebre y la tortuga
  - Del señor Sol y la señora Luna
  - El león y los ratones
  - Caperucita Roja
  - Los tres cerditos
  - Los patitos de la señora Pata
  - La pequeña oruga glotona
- **Controles sencillos** para reproducir, pausar y reiniciar los videos.
- **Cambio de video**: Permite seleccionar entre diferentes videos.
- **Miniaturas** de video: Cada video muestra una miniatura que anticipa su contenido.

## Requisitos del Sistema
- **Versión mínima de Android**: 5.0 (Lollipop, `minSdkVersion 21`)
- **Versión de destino de Android**: 13 (`targetSdkVersion 33`)

## Instalación
1. **Descarga el APK** desde la sección de [Releases](https://github.com/0xMoonrise/Cuentos/releases) en GitHub.
2. En tu dispositivo Android, activa la opción de instalar aplicaciones desde fuentes desconocidas, si aún no está activada.
3. Abre el archivo APK descargado y sigue las instrucciones de instalación.

## Instrucciones de Uso
- **Reproducir**: Pulsa el botón de reproducción para iniciar un cuento.
- **Reinciar**: Utiliza el botón de reinciar para volver a cargar un cuendo desde el inicio.
- **Cambiar Video**:  Cambia el video a uno dentro de la colección de videos, y selecciona el que desees ver.

## Estructura del Proyecto
La aplicación está desarrollada en **Kotlin** y sigue una estructura de proyecto de Android. Los elementos clave incluyen:

- **MainActivity.kt**: Controla la lógica principal de la app, incluyendo la reproducción de videos, las animaciones y el cambio de cuentos.
- **Res (Resources)**:
  - **layout/activity_main.xml**: Define la estructura de la interfaz, incluyendo los botones de reproducción, cambio de video y el telón.
  - **drawable/**: Incluye las imágenes y recursos visuales, como el telón.
  - **raw/**: Carpeta donde se almacenan los archivos de video de los cuentos.
  

